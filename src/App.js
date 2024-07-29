// src/App.js
import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import DropdownPage from "./pages/DropdownPage";
import Page2 from "./pages/Page2";
import Page3 from "./pages/Page3";
import "./App.css";

const App = () => {
  const [workflowName, setWorkflowName] = useState("");

  return (
    <Router>
      <div className="App">
        <div>
          <Link to="/">Home</Link>
        </div>
        <Routes>
          <Route
            path="/"
            element={<DropdownPage setWorkflowName={setWorkflowName} />}
          />
          <Route path="/2" element={<Page2 />} />
          <Route path="/3" element={<Page3 />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
