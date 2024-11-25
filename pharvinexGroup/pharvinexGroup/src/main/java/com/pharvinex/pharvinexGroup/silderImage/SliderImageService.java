package com.pharvinex.pharvinexGroup.silderImage;

import java.util.List;
import java.util.Optional;

public interface SliderImageService {
    SliderImage createImage(SliderImage image);
    Optional<SliderImage> getImageById(int id); // Added method for fetching images by ID
    List<SliderImage> getAllImages(); // Added method for fetching all images

    SliderImage updateImage(int id, SliderImage image); // Update an existing slider image

    void deleteImage(int id); // Delete an image by ID

    // Fetch slider images for client-side display only
    List<SliderImage> getImagesForClient(); // New method for fetching images for clients
}