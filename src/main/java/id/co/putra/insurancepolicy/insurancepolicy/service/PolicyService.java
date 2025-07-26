package id.co.putra.insurancepolicy.insurancepolicy.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyRequest;
import id.co.putra.insurancepolicy.insurancepolicy.dto.PolicyResponse;
import id.co.putra.insurancepolicy.insurancepolicy.exception.PutraCustomException;

public interface PolicyService {

    public PolicyResponse create(PolicyRequest request) throws PutraCustomException;

    public PolicyResponse update(@PathVariable Long id, PolicyRequest request) throws PutraCustomException;

    public List<PolicyResponse> getAll() throws PutraCustomException;

    public PolicyResponse getById(@PathVariable Long id) throws PutraCustomException;

}
