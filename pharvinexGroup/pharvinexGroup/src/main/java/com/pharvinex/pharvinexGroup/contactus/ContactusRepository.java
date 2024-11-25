package com.pharvinex.pharvinexGroup.contactus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactusRepository extends JpaRepository<Contactus, Integer> {
}
