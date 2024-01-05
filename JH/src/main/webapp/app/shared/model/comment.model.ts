import { IVideo } from 'app/shared/model/video.model';
import { IUserProfile } from 'app/shared/model/user-profile.model';

export interface IComment {
  id?: number;
  post?: string | null;
  thumbsUp?: number | null;
  video?: IVideo | null;
  postedBy?: IUserProfile | null;
}

export const defaultValue: Readonly<IComment> = {};
