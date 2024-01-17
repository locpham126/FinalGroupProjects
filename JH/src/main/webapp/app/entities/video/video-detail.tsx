import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col, CardColumns, Container } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getEntity } from './video.reducer';
import { useNavigate } from 'react-router-dom';
import 'app/userhome/cssfiles/videopreview.scss';
import Request from 'app/userhome/requests';
import axios from 'app/userhome/axios';


export const VideoDetail = () => {
  const dispatch = useAppDispatch();
  const { id } = useParams<'id'>();
  const [comments, setComments] = useState([]);
  const [users, setUsers] = useState([]);
  
  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  // async function to set comments into array
  useEffect(() => {
    async function fetchData() {
      const request = await axios.get(Request.fetchComments);
      const videoEntityId: bigint = id as unknown as bigint;
      let temp = [];
      for(let i = 0; i < request.data.length; i++){
        let videoId = request.data[i].video.id;
        if(videoId == videoEntityId){
          temp.push(request.data[i]);
        }
      }
      setComments(temp);
      return request;
    }
    fetchData();
  }, []);
  // comments.forEach(comment => {
  //   console.log(comment.postedBy.id);
  // });
  // async function to match user profile to comment
  useEffect(() => {
    async function fetchData() {
      const request = await axios.get(Request.fetchUser);
      let temp = [];
      setUsers(request.data);
       return request;
    }
    fetchData();
  }, []);
 console.log(users);

  //Functions
  function goBack() {
    window.history.back();
  }

    // Function to post comment  
    function openCommentModule() {
    // Basic example of a modal for comment input
    const temp = Math.floor(Math.random() * 3)+1;
    const comment = prompt("Add a comment (200 characters or less):");
    if (comment !== null && comment.length <= 200) {
    // Check if comment is valid
    async function postComment() {
      try{
      const request = await axios.post(Request.fetchComments, {
        method: "POST",
        post: comment, 
        postedBy: {
          id: temp
        },
        thumbsup: null,
        video: {
          id: id
        },
      });
      return request;
    } catch(err){
      console.log(err);
    }
    }
    postComment();
    alert("Comment added: " + comment);
  } else if (comment !== null) {
    alert("Error: Comment must be 200 characters or less.");
  }
  }

    let thumbsUpClickCount = 0;

    function thumbsUpDown() {
    // Change button text based on click count
    const thumbsButton = document.getElementById("thumbsButton");
    thumbsUpClickCount++;
    if (thumbsUpClickCount === 1) {
    thumbsButton.innerText = "Like";
  } else if (thumbsUpClickCount === 2) {
    thumbsButton.innerText = "Dislike";
  } else {
    thumbsButton.innerText = "Mid";
    thumbsUpClickCount = 0;
  }
  }
  //   function addToPlaylist() {
  //   // Change image for the "Add to My Playlist" button
  //   const playlistButton = document.getElementById("playlistButton");
  //   playlistButton.src = "path/to/added-to-playlist-image.jpg";
  // }
  // Functions

  
  const videoEntity = useAppSelector(state => state.video.entity);

  return (
    <Container>
      {/* <center>
      <img src={videoEntity.imageURL} alt="Video Preview" className="video-image"/>
      </center> */}
      <h1 className="video-title">{videoEntity.title}</h1>
  {/* <Button class="play-button" tag={Link} to={videoEntity.videoURL}>Play Video</Button> */}

    <Row>
    <Col md={3}>
  <img src={videoEntity.imageURL} alt="Video Preview" className="video-image"/>
  </Col>
  <Col md={6}>
  {videoEntity.videoURL && (
    <video  className='video-player' controls>
      <source src={videoEntity.videoURL} type="video/mp4"/>
    </video>
    )}
    </Col> 
    </Row>
    
    {/* <Row> */}
    <div className="video-details">
        <p>Release Year: {videoEntity.releaseYear}</p>
        <p>Classification: {videoEntity.classification}</p>
        <p>Duration: {videoEntity.duration} min</p>
        <div>{videoEntity.classification === 'TV Series' && (
        <p>Episode: {videoEntity.episode}</p> &&
        <p>Season: {videoEntity.season}</p>
        )}
        <p>Rating: {videoEntity.rating}</p>
        </div>
      </div>
     <div className="video-description">
     <p>Description: {videoEntity.description}</p> 
 </div>

    <div className='video-description'>
      Comments:
    {users.map
    (user => (  
      <p key = {user.id} className='video-comments'>
    {comments.map(comment => (
        <div>
        {user.id ===comment.postedBy.id && <p key = {comment.id} className='video-comments'>
         {user.userName}: {comment.post}
        </p>}
        </div>
      ))}
      </p>
      ))}
    </div>
    <Button className="comment-button" onClick={thumbsUpDown} id="thumbsButton">Like</Button>
    <Button className="comment-button" onClick={openCommentModule}>Add a Comment</Button> 
    <Button onClick={goBack}>Back</Button> 

    {/* </Row> */}
    </Container>
  );
};

export default VideoDetail;
