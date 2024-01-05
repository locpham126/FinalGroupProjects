package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.CommentTestSamples.*;
import static com.mycompany.myapp.domain.PlaylistTestSamples.*;
import static com.mycompany.myapp.domain.UserProfileTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class UserProfileTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProfile.class);
        UserProfile userProfile1 = getUserProfileSample1();
        UserProfile userProfile2 = new UserProfile();
        assertThat(userProfile1).isNotEqualTo(userProfile2);

        userProfile2.setId(userProfile1.getId());
        assertThat(userProfile1).isEqualTo(userProfile2);

        userProfile2 = getUserProfileSample2();
        assertThat(userProfile1).isNotEqualTo(userProfile2);
    }

    @Test
    void playlistTest() throws Exception {
        UserProfile userProfile = getUserProfileRandomSampleGenerator();
        Playlist playlistBack = getPlaylistRandomSampleGenerator();

        userProfile.setPlaylist(playlistBack);
        assertThat(userProfile.getPlaylist()).isEqualTo(playlistBack);

        userProfile.playlist(null);
        assertThat(userProfile.getPlaylist()).isNull();
    }

    @Test
    void commentsTest() throws Exception {
        UserProfile userProfile = getUserProfileRandomSampleGenerator();
        Comment commentBack = getCommentRandomSampleGenerator();

        userProfile.addComments(commentBack);
        assertThat(userProfile.getComments()).containsOnly(commentBack);
        assertThat(commentBack.getPostedBy()).isEqualTo(userProfile);

        userProfile.removeComments(commentBack);
        assertThat(userProfile.getComments()).doesNotContain(commentBack);
        assertThat(commentBack.getPostedBy()).isNull();

        userProfile.comments(new HashSet<>(Set.of(commentBack)));
        assertThat(userProfile.getComments()).containsOnly(commentBack);
        assertThat(commentBack.getPostedBy()).isEqualTo(userProfile);

        userProfile.setComments(new HashSet<>());
        assertThat(userProfile.getComments()).doesNotContain(commentBack);
        assertThat(commentBack.getPostedBy()).isNull();
    }
}
