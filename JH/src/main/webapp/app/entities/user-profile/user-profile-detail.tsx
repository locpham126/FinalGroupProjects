import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './user-profile.reducer';

export const UserProfileDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const userProfileEntity = useAppSelector(state => state.userProfile.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userProfileDetailsHeading">
          <Translate contentKey="jhApp.userProfile.detail.title">UserProfile</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.id}</dd>
          <dt>
            <span id="userName">
              <Translate contentKey="jhApp.userProfile.userName">User Name</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.userName}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="jhApp.userProfile.firstName">First Name</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="jhApp.userProfile.lastName">Last Name</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.lastName}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="jhApp.userProfile.email">Email</Translate>
            </span>
          </dt>
          <dd>{userProfileEntity.email}</dd>
          <dt>
            <Translate contentKey="jhApp.userProfile.playlist">Playlist</Translate>
          </dt>
          <dd>{userProfileEntity.playlist ? userProfileEntity.playlist.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-profile" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-profile/${userProfileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UserProfileDetail;
