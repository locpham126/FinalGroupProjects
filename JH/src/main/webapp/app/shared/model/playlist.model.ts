import { IVideo } from 'app/shared/model/video.model';
import { IUserProfile } from 'app/shared/model/user-profile.model';

export interface IPlaylist {
  id?: number;
  videos?: IVideo[] | null;
  userProfile?: IUserProfile | null;
}

export const defaultValue: Readonly<IPlaylist> = {};
