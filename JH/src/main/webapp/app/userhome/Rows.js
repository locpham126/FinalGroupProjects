import React, {useState, useEffect} from 'react';
import axios from './axios';


function Rows( {title, fetchUrl} ) {
  const [videos, setVideos] = useState([]);
  useEffect(() => {
    async function fetchData(){
      //   'http://localhost:8080'
      const request = await axios.get(fetchUrl);
      setVideos(request.data.results);
      console.log(request);
      return request;
    }
    fetchData();
  }, [fetchUrl]);
  return(
    <div className = "row">
      {/*   title */}
      <h2>{title}</h2>
      <div className="row_names">
        {/* {videos.map(video => ( */}
        {/*   <li  key={video.id}>{video.title}</li> */}
        {/* ))} */}
      </div>
      {/* container -> posters */}
    </div>
  )
}

const videosLoader = async
export default Rows
