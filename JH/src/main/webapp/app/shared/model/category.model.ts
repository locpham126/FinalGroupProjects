import { IVideo } from 'app/shared/model/video.model';

export interface ICategory {
  id?: number;
  categoryName?: string | null;
  videos?: IVideo[] | null;
}

export const defaultValue: Readonly<ICategory> = {};
