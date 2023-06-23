package marko.kladionica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NumberMatch {

    private Integer numberMatchesHome;
    private Integer numberMatchesForeign;
    private Integer numberMatchesBingo;
    private Integer numberMatchesNotFound;


}
