package com.Arius.myNetwork.entity;

import com.Arius.myNetwork.config.AesEncryption;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contact {

    @Column( name = "contactId",unique = true,updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank(message = "Please enter their name")
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ ]*$")
    @Column( name = "contactName")
    private String name;

    @Convert(converter = AesEncryption.class)
    private Integer phoneNumber;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.\\-/+=@_ ]*$")
    @NotBlank(message = "Please enter their email for further contacting")
    @Column( name = "ContactEmail",unique = true)
    @Convert(converter = AesEncryption.class)
    private String email;

    @NotBlank(message = "Please enter their occupation for easier contacting")
    @Column( name = "contactJobTitle")
    private String jobTitle;

    @NotBlank(message = "Please enter company name for easy networking")
    @Column( name = "contactCompanyName")
    private String companyName;

    @NotBlank(message = "Please enter location for easier finding")
    @Column( name = "contactCompanyLocation")
    private String companyLocation;

    @Lob
    @Column( name = "contactProfile",unique = true)
    private byte[] profile;

    @Column( name = "inContact")
    @Enumerated(EnumType.STRING)
    private InContact stillInContact ;
}
