package lab.web.lab04.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "banks")
@NamedQueries({
        @NamedQuery(name = "Bank.findAll", query = "select b from Bank b order by b.id desc")
})
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "tel_number")
    private String telNumber;

}