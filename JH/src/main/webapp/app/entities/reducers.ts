import userProfile from 'app/entities/user-profile/user-profile.reducer';
import video from 'app/entities/video/video.reducer';
import playlist from 'app/entities/playlist/playlist.reducer';
import comment from 'app/entities/comment/comment.reducer';
import category from 'app/entities/category/category.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  userProfile,
  video,
  playlist,
  comment,
  category,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
