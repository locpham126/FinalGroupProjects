import React, {useState, useEffect} from 'react';
import axios from './axios';
import './AppUser.scss';



function Row( {categoryTitle, fetchUrl} ) {
  const [videos, setVideos] = useState([]);
  useEffect(() => {     //   'http://localhost:8080'
    //async function to fetch data into 
    async function fetchData(){
      const request = await axios.get(fetchUrl);
      console.log(request);
      setVideos(request.data.videos);
      return request;
    }
    fetchData();
  }, [fetchUrl]);

    // console.log(videos);
   
   if(!videos)
     return (
       <>
         <p>fetching videos...</p>
       </>
     );

  // const { title, backdrop_path } = 

  return(
    <div className = "row">
      {/*   title */}
      <h2>{categoryTitle}</h2>
      <div className="row_posters">
        {videos.map(video => (
          <img src={video.imageURL} className='resized-image' alt={video.title}/>
        ))}
      </div>
      {/* containt -> posters */}
      </div>
  )
}

export default Row
