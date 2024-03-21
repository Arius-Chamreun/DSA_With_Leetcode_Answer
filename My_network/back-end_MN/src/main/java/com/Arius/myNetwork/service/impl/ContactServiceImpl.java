/**
 * @author arashxr
 * @email ariuschamreun15@gmail.com
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
package com.Arius.myNetwork.service.impl;

import com.Arius.myNetwork.entity.Contact;
import com.Arius.myNetwork.entity.InContact;
import com.Arius.myNetwork.repo.ContactRepo;
import com.Arius.myNetwork.service.ContactService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class ContactServiceImpl implements ContactService {

    private final ContactRepo contactRepo;
    public static final String photoDir = System.getProperty("user.home") + "/Downloads/Contacts/";

    @Autowired
    public ContactServiceImpl(ContactRepo contactRepo){
        this.contactRepo = contactRepo;
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    public Page<Contact> findAll(int page, int size) {
        // Sort.by() use the field name not the column name of the entity
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    @Override
    public Optional<Contact> showById(Long id) {
        return contactRepo.findById(id);
    }

    @Override
    public void deleteContactByNameAndId(String name, Long id) {
        contactRepo.deleteByNameAndId(name,id);
    }

    public Contact uploadPhoto(Long id,  MultipartFile file){
        Contact contact = contactRepo.findById(id).orElseThrow(EntityExistsException::new);
        byte[] image;
        try {
            if(file == null || file.isEmpty()){
                String defaultImage = "/home/arashxr/Downloads/defaultImage.webp";
                image = Files.readAllBytes(Paths.get(defaultImage));
            }else
            {
                image = file.getBytes();
            photoFunction.apply(id,file);
            }
            contact.setProfile(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return contactRepo.save(contact);
    }


    @Override
    @org.springframework.transaction.annotation.Transactional
    public Contact updateInfo(Long id, String name, Integer number, String email, MultipartFile image,
                              InContact inContact, String companyName, String companyLocation, String jobTitle) {

        Contact contacts = contactRepo.findById(id).orElseThrow(EntityExistsException::new);
        if(name != null) contacts.setName(name);
        if(number != null) contacts.setPhoneNumber(number);
        if(email != null) contacts.setEmail(email);
        if(inContact != null) contacts.setStillInContact(inContact);
        if(companyName != null) contacts.setCompanyName(companyName);
        if(companyLocation != null) contacts.setCompanyLocation(companyLocation);
        if(jobTitle != null) contacts.setJobTitle(jobTitle);
       if(image != null){
           try {
               photoFunction.apply(id,image);
           contacts.setProfile(image.getBytes());
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }

        return contactRepo.save(contacts);
    }

    private final Function<String,String> fileExtension = fileName -> Optional.of(fileName).filter(
                name -> name.contains("."))
                .map(name -> "." + name.substring(fileName.lastIndexOf(".") +1))
                .orElse(".png");

         final BiFunction<Long,MultipartFile,byte[]> photoFunction = (id, image) -> {
        try {
            Path fileStorageLocation = Paths.get(photoDir).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) Files.createDirectories(fileStorageLocation);

            Files.copy(image.getInputStream(),
        fileStorageLocation.resolve(id + fileExtension.apply(image.getOriginalFilename())),REPLACE_EXISTING);
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/contacts/image/" + id + fileExtension.apply(image.getOriginalFilename()))
                .toUriString()
                .getBytes();

        }catch (IOException  exception){
            throw new RuntimeException("Error while saving image");
        }
        };
}
