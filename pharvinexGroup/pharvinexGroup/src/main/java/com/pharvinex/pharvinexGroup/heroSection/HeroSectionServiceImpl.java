package com.pharvinex.pharvinexGroup.heroSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroSectionServiceImpl implements HeroSectionService {

    private final HeroSectionRepository heroSectionRepository;

    @Autowired
    public HeroSectionServiceImpl(HeroSectionRepository heroSectionRepository) {
        this.heroSectionRepository = heroSectionRepository;
    }

    @Override
    public HeroSection saveHeroSection(HeroSection heroSection) {
        return heroSectionRepository.save(heroSection);
    }
    @Override
    public List<HeroSection> getAllHeroSectionsImages() {
        // This could be customized based on your requirements, e.g., filtering out only sections with images
        return heroSectionRepository.findAll(); // Or a specific query to retrieve only those with images
    }


    @Override
    public List<HeroSection> getAllHeroSections() {
        return heroSectionRepository.findAll();
    }

    @Override
    public HeroSection getHeroSectionById(Long id) {
        return heroSectionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteHeroSection(Long id) {
        heroSectionRepository.deleteById(id);
    }

    @Override
    public HeroSection updateHeroSection(Long id, HeroSection heroSection) {
        HeroSection existingHeroSection = getHeroSectionById(id);
        if (existingHeroSection != null) {
            existingHeroSection.setHeading(heroSection.getHeading());
            existingHeroSection.setLogo(heroSection.getLogo());
            existingHeroSection.setContent(heroSection.getContent());
            return saveHeroSection(existingHeroSection);
        }
        return null;
    }
}
