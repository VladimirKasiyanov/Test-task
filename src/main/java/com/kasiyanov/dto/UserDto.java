package com.kasiyanov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String surname;
    private String email;
    private List<String> roles;
    private List<String> phones;

    @Override
    public String toString() {
        return "'name=" + name + "'" +
                ", 'surname=" + surname + "'" +
                ", 'email=" + email + "'" +
                ", 'roles=" + roles + "'" +
                ", 'phones=" + phones + "'";
    }
}
