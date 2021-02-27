package org.aws.demo.stepfunction;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestDTO {

    private String fname;

    private String lname;

    private Boolean isJava;
}
