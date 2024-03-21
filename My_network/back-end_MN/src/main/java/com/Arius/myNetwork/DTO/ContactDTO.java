/**
 * @author arashxr
 * @email oberon15th@gmail.com
 * @github <a href="https://github.com/Unrex5">Arius Github</a>
 */
package com.Arius.myNetwork.DTO;

import com.Arius.myNetwork.entity.InContact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private Long id;

    @NotBlank(message = "Please enter their name")
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ ]*$")
    private String name;

    private Integer phoneNumber;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ ]*$")
    @NotBlank(message = "Please enter their email for further contacting")
    private String email;

    @NotBlank(message = "Please enter their occupation for easier contacting")
    private String jobTitle;

    @NotBlank(message = "Please enter company name for easy networking")
    private String companyName;

    @NotBlank(message = "Please enter location for easier finding")
    private String companyLocation;

    private byte[] profile;

    private InContact stillInContact;
}
