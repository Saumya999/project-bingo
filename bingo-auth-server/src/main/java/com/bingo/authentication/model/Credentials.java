package com.bingo.authentication.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Credentials {

    @Id
    private String userName;

    private String password;
}
