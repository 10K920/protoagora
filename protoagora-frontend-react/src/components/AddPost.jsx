import { Box, Fab, Modal, Stack, TextField, Tooltip, Typography } from '@mui/material'
import React, { useState } from 'react'
import { Add as AddIcon } from "@mui/icons-material"
import styled from '@emotion/styled'

const StyledModal = styled(Modal)({
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
})
const AddPost = () => {
    const [openModal, setOpenModal] = useState(false)
    return (
        <>
            <Tooltip
                onClick={e => setOpenModal(true)}
                title="Add"
                sx={{
                    position: "fixed",
                    bottom: 20,
                    right: { xs: "calc(50% - 37px)", md: 30 }
                }}
            >
                <Fab color="primary" aria-label='add'>
                    <AddIcon />
                </Fab>
            </Tooltip>
            <StyledModal
                open={openModal}
                onClose={e => setOpenModal(false)}
                aria-labelledby="modal-modal-title"
                aria-describedby="modal-modal-description"
            >
                <Box width={400} height={600} bgcolor={"background.default"} color={"text.primary"} p={3} borderRadius={5}>
                    <Typography variant='h6' color="gray" textAlign="center">Create Topic</Typography>
                    <TextField
                        sx={{width:"100%"}}
                        id="outlined-multiline-static"
                        label="Content"
                        multiline
                        rows={4}
                        placeholder="Enter the content"
                    />
                    <Stack direction="row" gap={1} mt={2}>

                    </Stack>
                </Box>
            </StyledModal>
        </>
    )
}

export default AddPost