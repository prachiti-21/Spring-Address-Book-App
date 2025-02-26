package com.bridgelabz.SpringAddressBook.repository;



import com.bridgelabz.SpringAddressBook.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

