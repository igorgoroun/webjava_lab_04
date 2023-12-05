package lab.web.lab04.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link lab.web.lab04.data.PartnersBank}
 */

@Data
@Setter
@Getter
@NoArgsConstructor(force = true)
public class PartnersBankDto implements Serializable {
    @NotNull
    @Positive
    private Long id;
    @NotNull
    private String iban;
    private String new_bank_name;
    @NotNull
    private Long bank_id;
    @NotNull
    private Long partner_id;
}