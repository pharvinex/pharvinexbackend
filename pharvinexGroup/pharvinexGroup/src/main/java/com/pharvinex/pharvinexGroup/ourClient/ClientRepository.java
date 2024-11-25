package com.pharvinex.pharvinexGroup.ourClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ourClient, Integer> {
}
