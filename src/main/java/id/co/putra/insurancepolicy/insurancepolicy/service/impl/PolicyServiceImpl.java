package id.co.putra.insurancepolicy.insurancepolicy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyRequest;
import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyResponse;
import id.co.putra.insurancepolicy.insurancepolicy.exception.PutraCustomException;
import id.co.putra.insurancepolicy.insurancepolicy.model.Policy;
import id.co.putra.insurancepolicy.insurancepolicy.repository.PolicyRepository;
import id.co.putra.insurancepolicy.insurancepolicy.service.PolicyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService {
    
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public PolicyResponse create(PolicyRequest request) throws PutraCustomException {
        try {
            Policy policy = new Policy();
            policy.setHolderName(request.getHolderName());
            policy.setType(request.getType());
            policy.setStartDate(request.getStartDate());
            policy.setEndDate(request.getEndDate());
            policy.setPremiumAmount(request.getPremiumAmount());
    
            Policy savedPolicy = policyRepository.save(policy);
    
            return new PolicyResponse(
                savedPolicy.getId(),
                savedPolicy.getHolderName(),
                savedPolicy.getType(),
                savedPolicy.getStartDate().toString(),
                savedPolicy.getEndDate().toString(),
                savedPolicy.getPremiumAmount()
            );
        } catch (Exception ex) {
            log.error("Error from the api: {}", ex);
            throw new PutraCustomException("Failed to create policy: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ex);
        }
    }

    @Override
    public PolicyResponse update(Long id, PolicyRequest request) throws PutraCustomException {
        try {
            Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PutraCustomException("Policy with ID " + id + " not found"));
    
            policy.setHolderName(request.getHolderName());
            policy.setType(request.getType());
            policy.setStartDate(request.getStartDate());
            policy.setEndDate(request.getEndDate());
            policy.setPremiumAmount(request.getPremiumAmount());
    
            Policy updatedPolicy = policyRepository.save(policy);
    
            return new PolicyResponse(
                updatedPolicy.getId(),
                updatedPolicy.getHolderName(),
                updatedPolicy.getType(),
                updatedPolicy.getStartDate().toString(),
                updatedPolicy.getEndDate().toString(),
                updatedPolicy.getPremiumAmount()
            );
        } catch (PutraCustomException e) {
            throw new PutraCustomException(e.getMessage(), e.getStatusCode(), e);
        } catch (Exception e) {
            throw new PutraCustomException("Failed to update policy: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }    

    @Override
    public List<PolicyResponse> getAll() throws PutraCustomException {
        try {
            List<Policy> policies = policyRepository.findAll();

            return policies.stream()
                    .map(policy -> new PolicyResponse(
                            policy.getId(),
                            policy.getHolderName(),
                            policy.getType(),
                            policy.getStartDate().toString(),
                            policy.getEndDate().toString(),
                            policy.getPremiumAmount()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new PutraCustomException("Failed to retrieve policies: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }


    @Override
    public PolicyResponse getById(Long id) throws PutraCustomException {
        try {
            Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new PutraCustomException("Policy with ID " + id + " not found", HttpStatus.NOT_FOUND,
                new Exception()));
    
            return new PolicyResponse(
                policy.getId(),
                policy.getHolderName(),
                policy.getType(),
                policy.getStartDate().toString(),
                policy.getEndDate().toString(),
                policy.getPremiumAmount()
            );
        } catch (PutraCustomException e) {
            throw new PutraCustomException(e.getMessage(), e.getStatusCode(), e);
        } catch (Exception e) {
            throw new PutraCustomException("Failed to retrieve policy: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
    

}
