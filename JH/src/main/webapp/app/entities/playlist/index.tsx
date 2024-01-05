import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Playlist from './playlist';
import PlaylistDetail from './playlist-detail';
import PlaylistUpdate from './playlist-update';
import PlaylistDeleteDialog from './playlist-delete-dialog';

const PlaylistRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Playlist />} />
    <Route path="new" element={<PlaylistUpdate />} />
    <Route path=":id">
      <Route index element={<PlaylistDetail />} />
      <Route path="edit" element={<PlaylistUpdate />} />
      <Route path="delete" element={<PlaylistDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PlaylistRoutes;
