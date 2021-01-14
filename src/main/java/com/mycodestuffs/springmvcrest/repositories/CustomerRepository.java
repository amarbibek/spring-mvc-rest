package com.mycodestuffs.springmvcrest.repositories;

import com.mycodestuffs.springmvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
