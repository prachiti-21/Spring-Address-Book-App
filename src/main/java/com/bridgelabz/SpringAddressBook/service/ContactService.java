package com.bridgelabz.SpringAddressBook.service;



import com.bridgelabz.SpringAddressBook.model.Contact;
import com.bridgelabz.SpringAddressBook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(Long id, Contact updatedContact) {
        return getContactById(id).map(existingContact -> {
            existingContact.setName(updatedContact.getName());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setPhone(updatedContact.getPhone());
            return contactRepository.update(existingContact);
        });
    }

        public void deleteContact (Long id){
            contactRepository.deleteById(id);
        }
    }

