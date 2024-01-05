package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post")
    private String post;

    @Column(name = "thumbs_up")
    private Integer thumbsUp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "comments", "categories", "playlists" }, allowSetters = true)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "playlist", "comments" }, allowSetters = true)
    private UserProfile postedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Comment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPost() {
        return this.post;
    }

    public Comment post(String post) {
        this.setPost(post);
        return this;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getThumbsUp() {
        return this.thumbsUp;
    }

    public Comment thumbsUp(Integer thumbsUp) {
        this.setThumbsUp(thumbsUp);
        return this;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Comment video(Video video) {
        this.setVideo(video);
        return this;
    }

    public UserProfile getPostedBy() {
        return this.postedBy;
    }

    public void setPostedBy(UserProfile userProfile) {
        this.postedBy = userProfile;
    }

    public Comment postedBy(UserProfile userProfile) {
        this.setPostedBy(userProfile);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        return getId() != null && getId().equals(((Comment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", post='" + getPost() + "'" +
            ", thumbsUp=" + getThumbsUp() +
            "}";
    }
}
