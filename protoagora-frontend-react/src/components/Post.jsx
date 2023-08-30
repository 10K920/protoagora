import styled from '@emotion/styled';
import { Favorite, FavoriteBorder, MoreVert, Share } from '@mui/icons-material';
import { Avatar, Box, Button, ButtonGroup, Card, CardActions, CardContent, CardHeader, CardMedia, Checkbox, IconButton, Modal, Stack, TextField, Typography } from '@mui/material';
import React, { useEffect, useReducer, useState } from 'react'
import MuiGrid from '@mui/material/Grid';
import Divider from '@mui/material/Divider';
import axios from 'axios';

const StyledModal = styled(Modal)({
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
})

const Grid = styled(MuiGrid)(({ theme }) => ({
    width: '100%',
    ...theme.typography.body2,
    '& [role="separator"]': {
        margin: theme.spacing(0, 2),
    },
}));

const Post = ({ data, forceUpdate }) => {
    console.log("post rendered!");
    const [voteOptionModal, setVoteOptionModal] = useState(false);
    const [chosenOption, setChosenOption] = useState("");
    const topicId = data.id;


    const handleChooseOptionButtonClick = (selectedOption) => {
        setVoteOptionModal(true);
        console.log("Topic Id for this modal is " + topicId);
        setChosenOption(selectedOption);
    }

    const handleForceUpdate = () => {
        forceUpdate();
    }

    const confirmAndSendChoice = async () => {
        var option = true;
        if (chosenOption !== "Option 1") {
            option = false;
        }

        try {
            const response = await axios.post("http://localhost:8081/api/choose_side", {
                "topicId": topicId,
                "userId": 1010,
                "side": option
            });
            
            setVoteOptionModal(false);
            handleForceUpdate();
        } catch (error) {
            console.error('Error:', error)
        }
    };

    return (
        <>
            <Card sx={{ margin: 5 }}>
                <CardHeader
                    avatar={
                        <Avatar sx={{ bgcolor: "red" }} aria-label="recipe">
                            R
                        </Avatar>
                    }
                    action={
                        <IconButton aria-label="settings">
                            <MoreVert />
                        </IconButton>
                    }
                    title={data.topicTitle}
                    subheader={data.postTimestamp}
                />
                <CardContent>
                    <Typography variant="body3" color="text.secondary" dangerouslySetInnerHTML={{ __html: data.topicContent }}>
                    </Typography>

                    <Box
                        sx={{
                            // Set your desired background color
                            display: 'flex',
                            justifyContent: 'center', // Center horizontally
                            alignItems: 'center', // Center vertically
                            minHeight: '50px', // Adjust as needed
                        }}
                    >
                        <Grid container>
                            <Grid item xs>
                                <Typography variant='body1' color="text.secondary" align="center">
                                    {data.option1}
                                    <br />
                                    {data.option1Count} votes
                                </Typography>

                            </Grid>
                            <Divider orientation="vertical" flexItem></Divider>
                            <Grid item xs>
                                <Typography variant='body1' color="text.secondary" align="center">
                                    {data.option2}
                                    <br />
                                    {data.option2Count} votes
                                </Typography>
                            </Grid>

                        </Grid>
                    </Box>

                </CardContent>
                <CardActions disableSpacing>
                    <IconButton aria-label="add to favorites">
                        <Checkbox icon={<FavoriteBorder />} checkedIcon={<Favorite sx={{ color: "red" }} />} />
                    </IconButton>
                    <IconButton aria-label="share">
                        <Share />
                    </IconButton>
                </CardActions>
                <ButtonGroup
                    fullWidth
                    variant="outlined"
                    aria-label="outlined primary button group"
                >
                    <Button onClick={e => handleChooseOptionButtonClick("Option 1")}>Option 1</Button>
                    <Button onClick={e => handleChooseOptionButtonClick("Option 2")}>Option 2</Button>
                </ButtonGroup>
            </Card>
            <StyledModal
                open={voteOptionModal}
                onClose={e => setVoteOptionModal(false)}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box
                    width={400}
                    height={300}
                    bgcolor={"background.default"}
                    color={"text.primary"}
                    p={3}
                    borderRadius={5}
                    align="center"
                    alignItems="center"
                >
                    <Typography variant='h6' color="gray" textAlign="center">Are you voting for {chosenOption}?</Typography>

                    <Box sx={{ '& button': { m: 1 } }}>
                        <Button
                            onClick={e => confirmAndSendChoice()}
                            variant="outlined"
                            size="medium"
                        >
                            Yes
                        </Button>
                        <Button
                            onClick={e => setVoteOptionModal(false)}
                            variant="outlined"
                            size="medium"
                        >
                            No
                        </Button>
                    </Box>
                </Box>
            </StyledModal>
        </>
    )
}

export default Post