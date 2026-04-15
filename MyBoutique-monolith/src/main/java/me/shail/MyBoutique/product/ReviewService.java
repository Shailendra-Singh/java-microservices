package me.shail.MyBoutique.product;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.shail.MyBoutique.common.dto.ReviewDto;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewDto> findAll() {
        log.debug("Request to get all Reviews");
        return this.reviewRepository.findAll().stream().map(ReviewService::mapToDto).toList();
    }

    @Transactional(readOnly = true)
    public ReviewDto findById(Long id) {
        log.debug("Request to get Review: {}", id);
        return this.reviewRepository.findById(id).map(ReviewService::mapToDto).orElse(null);
    }

    public ReviewDto create(ReviewDto reviewDto) {
        log.debug("Request to create Review: {}", reviewDto);
        return mapToDto(this.reviewRepository.save(
                new Review(
                        reviewDto.title(),
                        reviewDto.description(),
                        reviewDto.rating())));
    }

    public void delete(Long id) {
        log.debug("Request to delete Review: {}", id);
        this.reviewRepository.deleteById(id);
    }

    public static ReviewDto mapToDto(Review review) {
        if (review != null) {
            return new ReviewDto(
                    review.getId(),
                    review.getTitle(),
                    review.getDescription(),
                    review.getRating());
        }

        return null;
    }
}
