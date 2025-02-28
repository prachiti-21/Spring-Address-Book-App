package com.bridgelabz.SpringAddressBook.repository;



import com.bridgelabz.SpringAddressBook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
     Contact update(Contact contact);
     Contact saveContact(Contact contact);
    }



