package edu.vn.hcmuaf.ebook.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    double price;
    @ManyToOne
    Category category;
    String author;
    String content;
    String linkFileOnl;
    String linkImgOnl;
    String linkThumbnail;
    int pages;
    String url;
    @UpdateTimestamp
    Date updateAt;
}
