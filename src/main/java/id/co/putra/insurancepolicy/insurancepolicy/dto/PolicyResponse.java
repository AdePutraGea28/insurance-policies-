package id.co.putra.insurancepolicy.insurancepolicy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class PolicyResponse {
    private Long id;
    private String holderName;
    private String type;
    private String startDate;
    private String endDate;
    private BigDecimal premiumAmount;
}
