package lab.web.lab04.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Partner {
    private int id;
    private String name;
    private String address;
    private String tel_number;
    private String itn;
    private String reg;
    private String notes;
}
