package com.pharvinex.pharvinexGroup.heroSection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroSectionRepository extends JpaRepository<HeroSection, Long> {
}