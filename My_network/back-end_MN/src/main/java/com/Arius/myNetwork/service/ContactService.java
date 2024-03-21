/**
 * @author arashxr
 * @email ariuschamreun15@gmail.com
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
package com.Arius.myNetwork.service;

import com.Arius.myNetwork.entity.Contact;
import com.Arius.myNetwork.entity.InContact;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

public interface ContactService {
     Contact createContact(Contact contact);
     Page<Contact> findAll(int page, int size);
     Optional<Contact> showById(Long id);
     void deleteContactByNameAndId(String name,Long id);
     Contact uploadPhoto(Long id, @NotNull MultipartFile file);
     Contact updateInfo(Long id, String name, Integer number, String email, MultipartFile image,
                         InContact inContact, String companyName, String companyLocation, String jobTitle);
}
