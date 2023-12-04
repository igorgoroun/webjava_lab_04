package lab.web.lab04.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    private int id;
    private int partner_id;
    private String partner_name = null;
    private String iban;
    private int bank_id;
    private String bank_name = null;
    private Boolean actual = Boolean.FALSE;
}
