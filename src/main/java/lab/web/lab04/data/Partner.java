package lab.web.lab04.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "partners")
@NamedQueries({
        @NamedQuery(name = "Partner.findAll", query = "select p from Partner p")
})
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Lob
    @Column(name = "address", nullable = false)
    private String address;

    @Lob
    @Column(name = "tel_number")
    private String telNumber;

    @Lob
    @Column(name = "itn")
    private String itn;

    @Lob
    @Column(name = "reg")
    private String reg;

    @Lob
    @Column(name = "notes")
    private String notes;

}