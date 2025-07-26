package id.co.putra.insurancepolicy.insurancepolicy.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyRequest;
import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyResponse;
import id.co.putra.insurancepolicy.insurancepolicy.exception.PutraCustomException;
import id.co.putra.insurancepolicy.insurancepolicy.model.Policy;
import id.co.putra.insurancepolicy.insurancepolicy.repository.PolicyRepository;
import id.co.putra.insurancepolicy.insurancepolicy.service.impl.PolicyServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PolicyServiceImplTest {

    @Mock
    private PolicyRepository policyRepository;

    @InjectMocks
    private PolicyServiceImpl policyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePolicy_Success() throws PutraCustomException {
        // Arrange
        PolicyRequest request = new PolicyRequest();
        request.setHolderName("John Doe");
        request.setType("Health");
        request.setStartDate(LocalDate.of(2025, 1, 1));
        request.setEndDate(LocalDate.of(2026, 1, 1));
        request.setPremiumAmount(BigDecimal.valueOf(1000));

        Policy mockSavedPolicy = new Policy();
        mockSavedPolicy.setId(1L);
        mockSavedPolicy.setHolderName("John Doe");
        mockSavedPolicy.setType("Health");
        mockSavedPolicy.setStartDate(request.getStartDate());
        mockSavedPolicy.setEndDate(request.getEndDate());
        mockSavedPolicy.setPremiumAmount(request.getPremiumAmount());

        when(policyRepository.save(any(Policy.class))).thenReturn(mockSavedPolicy);

        // Act
        PolicyResponse response = policyService.create(request);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("John Doe", response.getHolderName());
        assertEquals("Health", response.getType());
        assertEquals("2025-01-01", response.getStartDate());
        assertEquals("2026-01-01", response.getEndDate());
        assertEquals(BigDecimal.valueOf(1000), response.getPremiumAmount());

        verify(policyRepository, times(1)).save(any(Policy.class));
    }

    @Test
    public void testCreatePolicy_Exception() {
        // Arrange
        PolicyRequest request = new PolicyRequest();
        request.setHolderName("Invalid");

        when(policyRepository.save(any(Policy.class))).thenThrow(new RuntimeException("DB error"));

        // Act & Assert
        PutraCustomException ex = assertThrows(PutraCustomException.class, () -> {
            policyService.create(request);
        });

        assertTrue(ex.getMessage().contains("Failed to create policy"));
        verify(policyRepository, times(1)).save(any(Policy.class));
    }

    @Test
    void testUpdatePolicy_Success() throws Exception {
        // Given
        Long id = 1L;
        Policy existingPolicy = new Policy();
        existingPolicy.setId(id);
        existingPolicy.setHolderName("Old Name");
        existingPolicy.setType("OLD_TYPE");
        existingPolicy.setStartDate(LocalDate.of(2023, 1, 1));
        existingPolicy.setEndDate(LocalDate.of(2023, 12, 31));
        existingPolicy.setPremiumAmount(new BigDecimal("1000"));

        PolicyRequest updateRequest = new PolicyRequest();
        updateRequest.setHolderName("New Name");
        updateRequest.setType("NEW_TYPE");
        updateRequest.setStartDate(LocalDate.of(2024, 1, 1));
        updateRequest.setEndDate(LocalDate.of(2024, 12, 31));
        updateRequest.setPremiumAmount(new BigDecimal("2000"));

        Policy updatedPolicy = new Policy();
        updatedPolicy.setId(id);
        updatedPolicy.setHolderName("New Name");
        updatedPolicy.setType("NEW_TYPE");
        updatedPolicy.setStartDate(updateRequest.getStartDate());
        updatedPolicy.setEndDate(updateRequest.getEndDate());
        updatedPolicy.setPremiumAmount(updateRequest.getPremiumAmount());

        // When
        when(policyRepository.findById(id)).thenReturn(Optional.of(existingPolicy));
        when(policyRepository.save(any(Policy.class))).thenReturn(updatedPolicy);

        // Then
        PolicyResponse result = policyService.update(id, updateRequest);

        assertEquals("New Name", result.getHolderName());
        assertEquals("NEW_TYPE", result.getType());
        assertEquals("2024-01-01", result.getStartDate());
        assertEquals("2024-12-31", result.getEndDate());
        assertEquals(new BigDecimal("2000"), result.getPremiumAmount());
    }

    @Test
    void testUpdatePolicy_NotFound() {
        // Given
        Long id = 99L;
        PolicyRequest request = new PolicyRequest();

        when(policyRepository.findById(id)).thenReturn(Optional.empty());

        // Then
        PutraCustomException thrown = assertThrows(PutraCustomException.class, () -> {
            policyService.update(id, request);
        });

        assertEquals("Policy with ID 99 not found", thrown.getMessage());
    }

    @Test
    void testUpdatePolicy_ExceptionThrown() {
        // Given
        Long id = 1L;
        Policy existingPolicy = new Policy();
        existingPolicy.setId(id);

        PolicyRequest request = new PolicyRequest();
        request.setHolderName("Broken");

        when(policyRepository.findById(id)).thenReturn(Optional.of(existingPolicy));
        when(policyRepository.save(any(Policy.class))).thenThrow(new RuntimeException("Database down"));

        // Then
        PutraCustomException thrown = assertThrows(PutraCustomException.class, () -> {
            policyService.update(id, request);
        });

        assertTrue(thrown.getMessage().contains("Failed to update policy"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, thrown.getStatusCode());
    }

    @Test
    void testGetAllPolicies_Success() throws Exception {
        // Given
        Policy policy1 = new Policy();
        policy1.setId(1L);
        policy1.setHolderName("John Doe");
        policy1.setType("LIFE");
        policy1.setStartDate(LocalDate.of(2024, 1, 1));
        policy1.setEndDate(LocalDate.of(2024, 12, 31));
        policy1.setPremiumAmount(new BigDecimal("1000"));

        Policy policy2 = new Policy();
        policy2.setId(2L);
        policy2.setHolderName("Jane Smith");
        policy2.setType("HEALTH");
        policy2.setStartDate(LocalDate.of(2024, 2, 1));
        policy2.setEndDate(LocalDate.of(2025, 1, 31));
        policy2.setPremiumAmount(new BigDecimal("1500"));

        List<Policy> mockPolicies = Arrays.asList(policy1, policy2);

        when(policyRepository.findAll()).thenReturn(mockPolicies);

        // When
        List<PolicyResponse> result = policyService.getAll();

        // Then
        assertEquals(2, result.size());

        PolicyResponse res1 = result.get(0);
        assertEquals(1L, res1.getId());
        assertEquals("John Doe", res1.getHolderName());

        PolicyResponse res2 = result.get(1);
        assertEquals(2L, res2.getId());
        assertEquals("Jane Smith", res2.getHolderName());
    }

    @Test
    void testGetAllPolicies_ExceptionThrown() {
        // Given
        when(policyRepository.findAll()).thenThrow(new RuntimeException("Database error"));

        // Then
        PutraCustomException thrown = assertThrows(PutraCustomException.class, () -> {
            policyService.getAll();
        });

        assertTrue(thrown.getMessage().contains("Failed to retrieve policies"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, thrown.getStatusCode());
    }

    @Test
    void testGetById_Success() throws Exception {
        // Given
        Long policyId = 1L;
        Policy policy = new Policy();
        policy.setId(policyId);
        policy.setHolderName("John Doe");
        policy.setType("LIFE");
        policy.setStartDate(LocalDate.of(2024, 1, 1));
        policy.setEndDate(LocalDate.of(2024, 12, 31));
        policy.setPremiumAmount(new BigDecimal("1200"));

        when(policyRepository.findById(policyId)).thenReturn(Optional.of(policy));

        // When
        PolicyResponse response = policyService.getById(policyId);

        // Then
        assertNotNull(response);
        assertEquals(policyId, response.getId());
        assertEquals("John Doe", response.getHolderName());
        assertEquals("LIFE", response.getType());
        assertEquals("2024-01-01", response.getStartDate());
        assertEquals("2024-12-31", response.getEndDate());
        assertEquals(new BigDecimal("1200"), response.getPremiumAmount());
    }

    @Test
    void testGetById_NotFound() {
        // Given
        Long policyId = 99L;
        when(policyRepository.findById(policyId)).thenReturn(Optional.empty());

        // When
        PutraCustomException exception = assertThrows(PutraCustomException.class, () -> {
            policyService.getById(policyId);
        });

        // Then
        assertEquals("Policy with ID 99 not found", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testGetById_RepositoryThrowsUnexpectedException() {
        // Given
        Long policyId = 1L;
        when(policyRepository.findById(policyId)).thenThrow(new RuntimeException("DB down"));

        // When
        PutraCustomException exception = assertThrows(PutraCustomException.class, () -> {
            policyService.getById(policyId);
        });

        // Then
        assertTrue(exception.getMessage().contains("Failed to retrieve policy"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatusCode());
    }
}

