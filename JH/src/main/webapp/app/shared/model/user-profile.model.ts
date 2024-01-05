import { IPlaylist } from 'app/shared/model/playlist.model';
import { IComment } from 'app/shared/model/comment.model';

export interface IUserProfile {
  id?: number;
  userName?: string | null;
  firstName?: string | null;
  lastName?: string | null;
  email?: string | null;
  playlist?: IPlaylist | null;
  comments?: IComment[] | null;
}

export const defaultValue: Readonly<IUserProfile> = {};
