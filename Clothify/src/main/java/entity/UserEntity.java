package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Boolean isAdmin;
}
