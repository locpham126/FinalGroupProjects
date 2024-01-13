import React from 'react';
import './AppUser.scss';
import {Translate} from "react-jhipster";
import {useLocation, useNavigate} from "react-router-dom";
import {useAppDispatch} from "app/config/store";
import Row from "./Row";
import requests from "./requests";
import Rows from "app/userhome/Rows";

//page to reroute login.users
function AppUser(){
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();
  const routeToUserHome = () => {
    const path = `/userhome`;
    navigate(path);
  }
  routeToUserHome();
  return(
    <>
    <div className ="AppUser">
      <h1>
        Howdy! Welcome to VLA (Netflix Clone)
      </h1>
      <Rows title = "Action" fetchUrl={requests.fetchAction} />
      <Rows title = "Romance" fetchUrl={requests.fetchRomance}/>
      <Rows title = "Comedy" fetchUrl={requests.fetchComedy}/>
      <Rows title = "Fantasy" fetchUrl={requests.fetchAction} />
      <Rows title = "Animation" fetchUrl={requests.fetchRomance}/>
      <Rows title = "Horror" fetchUrl={requests.fetchComedy}/>
      <Rows title = "Sci-Fi" fetchUrl={requests.fetchAction} />
      <Rows title = "Drama" fetchUrl={requests.fetchRomance}/>
      <Rows title = "Adventure" fetchUrl={requests.fetchComedy}/>
      <Rows title = "Crime" fetchUrl={requests.fetchAction} />
      <Rows title = "Thriller" fetchUrl={requests.fetchRomance}/>
      <Rows title = "Music" fetchUrl={requests.fetchComedy}/>
      <Rows title = "Family" fetchUrl={requests.fetchAction} />
      <Rows title = "Sports" fetchUrl={requests.fetchRomance}/>
      <Rows title = "Biography" fetchUrl={requests.fetchComedy}/>
    </div>
    </>
  );
}

export default AppUser;
