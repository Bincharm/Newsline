package com.company.Newsline.model;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsModel {
    Long id;
    String headline;
    String newsBody;

    MultipartFile image; //it is needed for conversion the image
}
