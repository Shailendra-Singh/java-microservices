package me.shail.MyBoutique;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ReviewDto {
    private Long id;
    private String title;
    private String description;
    private Long rating;
}
