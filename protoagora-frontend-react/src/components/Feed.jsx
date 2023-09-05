import { Box } from '@mui/material';
import React, { useEffect, useReducer, useState } from 'react';
import Post from './Post';
import axios from 'axios';

const Feed = () => {
  const [postData, setPostData] = useState([]);
  const [reducerValue, forceUpdate] = useReducer(x => x + 1, 0);
  console.log("reducer value: " + reducerValue);

  useEffect(() => {
    axios.get('http://localhost:8080/api/topic/get_all_topics', {}, {
      headers: {
        
        "Access-Control-Allow-Origin": "http://localhost:3000",
      }
    })
      .then(response => {
        setPostData(response.data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      })
  }, [reducerValue]);

  const updateReducerValue = () => {
    forceUpdate();
  };

  return (
    <Box flex={4} p={2}>
      {postData.map(row => (
        <Post
          key={row.id}
          data={row}
          forceUpdate={updateReducerValue}
        />
      ))}
    </Box>
  )
}

export default Feed