package marko.kladionica.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reports")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Reports {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="date")
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
