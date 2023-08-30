import Feed from "../components/Feed";
import Sidebar from "../components/Sidebar";
import Rightbar from "../components/Rightbar";
import { Box, Stack, ThemeProvider, createTheme } from "@mui/material";
import Navbar from "../components/Navbar";
import AddPost from "../components/AddPost";
import { useState } from "react";

function Home() {
  const [theme, setTheme] = useState("light")

  const darkTheme = createTheme({
    palette: {
      mode: theme
    }
  })
  return (
    <div>
      <ThemeProvider theme={darkTheme}>
        <Box bgcolor={"background.default"} color={"text.primary"}>
          <Navbar />
          <Stack direction="row" spacing={2} justifyContent="space-between">
            <Sidebar setTheme={setTheme} theme={theme}/>
            <Feed />
          </Stack>
          <AddPost />
        </Box>
      </ThemeProvider>
    </div>
  );
}

export default Home;
