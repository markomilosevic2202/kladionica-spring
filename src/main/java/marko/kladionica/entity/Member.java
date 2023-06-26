package marko.kladionica.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="members")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private String userId;

    @Column(name="pw")
    private String pw;



    @Column(name="email")
    private String email;


}
