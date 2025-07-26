package id.co.putra.insurancepolicy.insurancepolicy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyRequest;
import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyResponse;
import id.co.putra.insurancepolicy.insurancepolicy.exception.PutraCustomException;
import id.co.putra.insurancepolicy.insurancepolicy.service.PolicyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/policy")
@Tag(name = "Policy", description = "API Policy")
public class InsurancePolicyController {

	@Autowired
	private PolicyService policyService;

    @PostMapping("create")
	@Operation(summary = "Create Policy", description = "Insert 1 Row Policy Data")
    public ResponseEntity<PolicyResponse> create(@Valid @RequestBody PolicyRequest request) throws PutraCustomException {
        return ResponseEntity.status(HttpStatus.CREATED).body(policyService.create(request));
    }

    @PutMapping("/update/{id}")
	@Operation(summary = "Update Policy", description = "Update 1 Policy Data")
    public ResponseEntity<PolicyResponse> update(@PathVariable Long id, @Valid @RequestBody PolicyRequest request) throws PutraCustomException {
		return ResponseEntity.status(HttpStatus.OK).body(policyService.update(id, request));
    }

    @GetMapping("/getAll")
	@Operation(summary = "Find All Policy", description = "Select All Row Policy Data")
    public ResponseEntity<List<PolicyResponse>> getAll() throws PutraCustomException {
		return ResponseEntity.status(HttpStatus.OK).body(policyService.getAll());
    }

    @GetMapping("/getById/{id}")
	@Operation(summary = "Get One Policy", description = "Get One Policy Data By ID")
    public ResponseEntity<PolicyResponse> getById(@PathVariable Long id) throws PutraCustomException {
		return ResponseEntity.status(HttpStatus.OK).body(policyService.getById(id));
    }
}
