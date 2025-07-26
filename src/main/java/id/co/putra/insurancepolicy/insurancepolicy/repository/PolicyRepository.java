package id.co.putra.insurancepolicy.insurancepolicy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.putra.insurancepolicy.insurancepolicy.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long>{

}
