package com.pharvinex.pharvinexGroup.heroSection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HeroSectionController {

    private final HeroSectionService heroSectionService;

    @Autowired
    public HeroSectionController(HeroSectionService heroSectionService) {
        this.heroSectionService = heroSectionService;
    }

    // Create a new HeroSection
    @PostMapping("/admin/create-hero-section")
    public ResponseEntity<HeroSection> createHeroSection(
            @RequestParam("heading") String heading,
            @RequestParam(value = "logo", required = false) MultipartFile logo,
            @RequestParam("content") String content) throws IOException {
        HeroSection heroSection = new HeroSection();
        heroSection.setHeading(heading);
        if (logo != null) {
            heroSection.setLogo(logo.getBytes());
        }
        heroSection.setContent(content);
        HeroSection savedHeroSection = heroSectionService.saveHeroSection(heroSection);
        return ResponseEntity.ok(savedHeroSection);
    }

    // Retrieve all HeroSections
    @GetMapping("/admin/list-hero-section")
    public ResponseEntity<List<HeroSection>> getAllHeroSections() {
        List<HeroSection> heroSections = heroSectionService.getAllHeroSections();
        return ResponseEntity.ok(heroSections);
    }

    // Retrieve a HeroSection by ID
    @GetMapping("/admin/list-hero-section/{id}")
    public ResponseEntity<HeroSection> getHeroSectionById(@PathVariable Long id) {
        HeroSection heroSection = heroSectionService.getHeroSectionById(id);
        if (heroSection == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(heroSection);
    }

    // Update an existing HeroSection
    @PutMapping("/admin/update-hero-section/{id}")
    public ResponseEntity<HeroSection> updateHeroSection(
            @PathVariable Long id,
            @RequestParam("heading") String heading,
            @RequestParam(value = "logo", required = false) MultipartFile logo,
            @RequestParam("content") String content) throws IOException {
        HeroSection existingHeroSection = heroSectionService.getHeroSectionById(id);
        if (existingHeroSection == null) {
            return ResponseEntity.notFound().build();
        }
        existingHeroSection.setHeading(heading);
        if (logo != null) {
            existingHeroSection.setLogo(logo.getBytes());
        }
        existingHeroSection.setContent(content);
        HeroSection updatedHeroSection = heroSectionService.updateHeroSection(id, existingHeroSection);
        return ResponseEntity.ok(updatedHeroSection);
    }

    // Delete a HeroSection by ID
    @DeleteMapping("/admin/delete-hero-section/{id}")
    public ResponseEntity<Void> deleteHeroSection(@PathVariable Long id) {
        HeroSection existingHeroSection = heroSectionService.getHeroSectionById(id);
        if (existingHeroSection == null) {
            return ResponseEntity.notFound().build();
        }
        heroSectionService.deleteHeroSection(id);
        return ResponseEntity.noContent().build();
    }



    // Retrieve all HeroSections with images
    @GetMapping("/public/list-hero-section-images")
    public ResponseEntity<List<HeroSection>> getAllHeroSectionsImages() {
        List<HeroSection> heroSectionsImages = heroSectionService.getAllHeroSectionsImages();
        return ResponseEntity.ok(heroSectionsImages);
    }
}