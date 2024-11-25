package com.pharvinex.pharvinexGroup.silderImage;

import com.pharvinex.pharvinexGroup.silderImage.SliderImage;
import com.pharvinex.pharvinexGroup.silderImage.SliderImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class SliderImageController {

    @Autowired
    private SliderImageService sliderImageService;

    // Endpoint to create a new slider image
    @PostMapping("/admin/create/slider-image")
    public ResponseEntity<SliderImage> createImage(@RequestParam("name") String name,
                                                   @RequestParam("displayPicture") MultipartFile displayPicture) {
        try {
            // Create a new SliderImage object
            SliderImage sliderImage = new SliderImage();
            sliderImage.setName(name);
            sliderImage.setDisplayPicture(displayPicture.getBytes()); // Convert MultipartFile to byte[]

            // Save the image using the service
            SliderImage createdImage = sliderImageService.createImage(sliderImage);
            return new ResponseEntity<>(createdImage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Handle exceptions
        }
    }

    // Endpoint to get an image by its ID
    @GetMapping("/admin/list-slider-image/{id}")
    public ResponseEntity<SliderImage> getImageById(@PathVariable int id) {
        Optional<SliderImage> sliderImage = sliderImageService.getImageById(id);
        return sliderImage.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to get all slider images
    @GetMapping("/admin/list-slider-image")
    public ResponseEntity<List<SliderImage>> getAllImages() {
        List<SliderImage> images = sliderImageService.getAllImages();
        return ResponseEntity.ok(images);
    }



    // Endpoint to update an existing slider image
    @PutMapping("/admin/update-slider-image/{id}")
    public ResponseEntity<SliderImage> updateImage(@PathVariable int id,
                                                   @RequestParam("name") String name,
                                                   @RequestParam(value = "displayPicture", required = false) MultipartFile displayPicture) {
        try {
            // Retrieve the existing image
            Optional<SliderImage> existingImage = sliderImageService.getImageById(id);
            if (existingImage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Update the fields of the existing image
            SliderImage sliderImage = existingImage.get();
            sliderImage.setName(name);
            if (displayPicture != null) {
                sliderImage.setDisplayPicture(displayPicture.getBytes());
            }

            // Save the updated image
            SliderImage updatedImage = sliderImageService.updateImage(id, sliderImage);
            return new ResponseEntity<>(updatedImage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Endpoint to delete a slider image by its ID
    @DeleteMapping("/admin/delete-slider-image/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) {
        try {
            // Check if the image exists
            Optional<SliderImage> existingImage = sliderImageService.getImageById(id);
            if (existingImage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            // Delete the image
            sliderImageService.deleteImage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return no content status
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/slider-image")
    public ResponseEntity<List<String>> getSliderImagesForClient() {
        List<SliderImage> images = sliderImageService.getImagesForClient();

        // Convert byte[] to Base64 encoded string
        List<String> encodedImages = images.stream()
                .map(image -> Base64.getEncoder().encodeToString(image.getDisplayPicture()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(encodedImages);
    }

}
