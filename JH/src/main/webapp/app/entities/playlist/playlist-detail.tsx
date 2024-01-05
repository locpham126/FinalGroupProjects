import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './playlist.reducer';

export const PlaylistDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const playlistEntity = useAppSelector(state => state.playlist.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="playlistDetailsHeading">Playlist</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{playlistEntity.id}</dd>
          <dt>Videos</dt>
          <dd>
            {playlistEntity.videos
              ? playlistEntity.videos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {playlistEntity.videos && i === playlistEntity.videos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/playlist" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/playlist/${playlistEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PlaylistDetail;
