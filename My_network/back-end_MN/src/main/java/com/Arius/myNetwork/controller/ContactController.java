/**
 * @author arashxr
 * @email ariuschamreun15@gmail.com
 * @github <a href="https://github.com/Arius-Chamreun">Github Url</a>
 */
package com.Arius.myNetwork.controller;
import com.Arius.myNetwork.DTO.ContactDTO;
import com.Arius.myNetwork.entity.Contact;
import com.Arius.myNetwork.entity.InContact;
import com.Arius.myNetwork.repo.ContactRepo;
import com.Arius.myNetwork.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api/contacts")
@Validated
public class ContactController {
    private final ModelMapper modelMapper;
    private final ContactService contactService;
    private final ContactRepo contactRepo;

    @Autowired
    public ContactController(ModelMapper modelMapper, ContactService contactService,
                             ContactRepo contactRepo ){
        this.modelMapper = modelMapper;
        this.contactService = contactService;
        this.contactRepo = contactRepo;
    }

    @PostMapping("/create")
    public ResponseEntity<ContactDTO> createContact(@RequestBody Contact contact){
            Contact contactOBJ = contactService.createContact(contact);
            ContactDTO contactDTO = modelMapper.map(contactOBJ,ContactDTO.class);
                    contactRepo.save(contactOBJ);
            return new ResponseEntity<>(contactDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContact(@PathVariable("id") Long id){
        Optional<Contact> contacts = contactService.showById(id);

        return contacts.map( contact  ->  {
            ContactDTO contactDTO = modelMapper.map(contact,ContactDTO.class);
            return new ResponseEntity<>(contactDTO,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ContactDTO>> getAllContact(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size){
        Page<Contact> contacts = contactService.findAll(page, size);
        if(contacts.isEmpty()) return ResponseEntity.notFound().build();

        Page<ContactDTO> contactDTOS = contacts.map(contact -> modelMapper.map(contact,ContactDTO.class));
        return new ResponseEntity<>(contactDTOS,HttpStatus.OK);
    }

    @PutMapping("/upload_image/{id}")
    public ResponseEntity<ContactDTO> uploadImage(@PathVariable("id") Long id,
                                                  @RequestParam(value = "image",required = false) MultipartFile image) {
        if(!contactRepo.existsById(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Contact updatedContact = contactService.uploadPhoto(id,image);
        ContactDTO contactDTO = modelMapper.map(updatedContact, ContactDTO.class);

            return ResponseEntity.ok(contactDTO);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<ContactDTO> updateInfo(
            @PathVariable("id") Long id,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "phoneNumber",required = false) Integer number,
            @RequestParam(value = "email;",required = false) String email,
            @RequestParam(value = "profile",required = false) MultipartFile image,
            @RequestParam(value = "stillInContact",required = false) InContact inContact,
            @RequestParam(value = "companyName",required = false) String companyName,
            @RequestParam(value = "companyLocation",required = false) String companyLocation,
            @RequestParam(value = "jobTitle",required = false) String jobTitle){

        if(!contactRepo.existsById(id)) return ResponseEntity.notFound().build();

        Contact contacts = contactService.updateInfo(id,name,number,email,image,inContact,
                companyName,companyLocation,jobTitle);
        ContactDTO contactDTO = modelMapper.map(contacts,ContactDTO.class);

        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/images/{id}",produces = {IMAGE_PNG_VALUE,IMAGE_JPEG_VALUE,IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getPhoto(@PathVariable("id") Long id){
        Optional<Contact> contacts = contactRepo.findById(id);
        String fourOFourImage = "/home/arashxr/Downloads/404.png";
        byte[] images;

        if (contacts.isPresent()) {
                images = contacts.get().getProfile();

                return new ResponseEntity<>(images, HttpStatus.OK);
        } else {
            try {
                images = Files.readAllBytes(Paths.get(fourOFourImage));
                return new ResponseEntity<>(images,HttpStatus.NOT_FOUND);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    @DeleteMapping("/delete/{id}/{name}")
    public ResponseEntity<String> deleteContact(@PathVariable("name") String name,
                                                @PathVariable("id") Long id){

        if(!contactRepo.existsById(id)) return new  ResponseEntity<>(
                "The person doesn't exists in the contact",
                      HttpStatus.NOT_FOUND);

        contactService.deleteContactByNameAndId(name, id);
        return new ResponseEntity<>("The person is successfully deleted from your contact", HttpStatus.OK);
    }
}
