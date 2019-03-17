package org.test.root.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;


@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "NEWS")
public class News implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_topic")
    private String nameTopic;

    @Column(name = "content")
    private String content;

    @Column(name = "publication_date")
    private String publicationDate;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, targetEntity = Category.class)
    @JoinColumn(name = "news_category")
    private Category category;
}
