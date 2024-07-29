import React from "react";
import { useLocation } from "react-router-dom";
import Navbar from "../components/Navbar";

const Page3 = () => {
  const location = useLocation();
  const workflowName = location.state?.workflowName || "";

  return (
    <div>
      <Navbar workflowName={workflowName} />
      <h2>Page 3 Content</h2>
    </div>
  );
};

export default Page3;
