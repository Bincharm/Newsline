package com.company.Newsline.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String headline;

    @CreationTimestamp
    @NotNull
    @Column
    private Date postDate = new Date();

    @NotNull
    @Column(length = 2000)
    private String newsBody;

    @Lob
    private Byte[] image;

}
