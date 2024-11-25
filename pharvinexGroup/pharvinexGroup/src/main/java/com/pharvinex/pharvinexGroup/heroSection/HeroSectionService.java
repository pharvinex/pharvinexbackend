package com.pharvinex.pharvinexGroup.heroSection;

import java.util.List;

public interface HeroSectionService {
    HeroSection saveHeroSection(HeroSection heroSection);
    List<HeroSection> getAllHeroSections();

    List<HeroSection> getAllHeroSectionsImages();
    HeroSection getHeroSectionById(Long id);
    void deleteHeroSection(Long id);
    HeroSection updateHeroSection(Long id, HeroSection heroSection);
}
