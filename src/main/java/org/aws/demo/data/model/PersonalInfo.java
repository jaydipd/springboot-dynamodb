package org.aws.demo.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private String Id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private Boolean shouldStore = false;
}
