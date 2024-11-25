package com.pharvinex.pharvinexGroup.silderImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SliderImageServiceImpl implements SliderImageService {

    @Autowired
    private SliderImageRepository sliderImageRepository;

    @Override
    public SliderImage createImage(SliderImage image) {
        return sliderImageRepository.save(image);
    }

    @Override
    public Optional<SliderImage> getImageById(int id) {
        return sliderImageRepository.findById(id);
    }

    @Override
    public List<SliderImage> getAllImages() {
        return sliderImageRepository.findAll();
    }

    @Override
    public SliderImage updateImage(int id, SliderImage image) {
        // Check if the image exists
        if (!sliderImageRepository.existsById(id)) {
            return null; // Or throw an exception
        }
        image.setId(id); // Assuming you have a setId method
        return sliderImageRepository.save(image); // Save updated image
    }

    @Override
    public void deleteImage(int id) {
        sliderImageRepository.deleteById(id);
    }

    @Override
    public List<SliderImage> getImagesForClient() {
        // Example: Fetch only images where the status is "published"
        return sliderImageRepository.findAll();
    }
}
