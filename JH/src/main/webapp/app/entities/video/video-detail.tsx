import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './video.reducer';

export const VideoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const videoEntity = useAppSelector(state => state.video.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="videoDetailsHeading">Video</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{videoEntity.id}</dd>
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{videoEntity.title}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{videoEntity.description}</dd>
          <dt>
            <span id="year">Year</span>
          </dt>
          <dd>{videoEntity.year}</dd>
          <dt>
            <span id="classification">Classification</span>
          </dt>
          <dd>{videoEntity.classification}</dd>
          <dt>
            <span id="duration">Duration</span>
          </dt>
          <dd>{videoEntity.duration}</dd>
          <dt>
            <span id="episode">Episode</span>
          </dt>
          <dd>{videoEntity.episode}</dd>
          <dt>
            <span id="season">Season</span>
          </dt>
          <dd>{videoEntity.season}</dd>
          <dt>
            <span id="rating">Rating</span>
          </dt>
          <dd>{videoEntity.rating}</dd>
          <dt>
            <span id="videoURL">Video URL</span>
          </dt>
          <dd>{videoEntity.videoURL}</dd>
        </dl>
        <Button tag={Link} to="/video" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/video/${videoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default VideoDetail;
