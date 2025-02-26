package com.bridgelabz.SpringAddressBook.controller;



import com.bridgelabz.SpringAddressBook.dto.ContactDTO;
import com.bridgelabz.SpringAddressBook.model.Contact;
import com.bridgelabz.SpringAddressBook.repository.ContactRepository;
import com.bridgelabz.SpringAddressBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    private ContactDTO convertToDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName(contact.getName());
        contactDTO.setPhone(contact.getPhone());
        return contactDTO;
    }
    // Convert DTO to Entity
    private Contact convertToEntity(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }
    // GET - Fetch All Contacts
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<List<ContactDTO>> getContactById(@PathVariable Long id) {
        List<ContactDTO> contacts = contactService.getAllContacts().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(contacts, HttpStatus.OK);

    }

    // POST - Create New Contact
    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = convertToEntity(contactDTO);
        Contact savedContact = contactService.saveContact(contact);
        return new ResponseEntity<>(convertToDTO(savedContact), HttpStatus.CREATED);
    }

    // PUT - Update Contact by ID
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Optional<Contact> existingContact = contactService.getContactById(id);

        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();
            contact.setName(contactDTO.getName());
            contact.setPhone(contactDTO.getPhone());

            Contact updatedContact = contactService.saveContact(contact);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE - Delete Contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

