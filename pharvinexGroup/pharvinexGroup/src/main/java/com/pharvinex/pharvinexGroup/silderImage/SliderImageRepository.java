package com.pharvinex.pharvinexGroup.silderImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderImageRepository extends JpaRepository<SliderImage, Integer> { // Changed Long to Integer
}