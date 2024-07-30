import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../styles/Page2.css";
import Navbar from "../components/Navbar";
import axios from "axios";

const Page2 = () => {
  const [jsonInput, setJsonInput] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();
  const location = useLocation();
  const workflowName = location.state?.workflowName || "Workflow Name";

  const handleSubmit = async () => {
    try {
      const parsedJson = JSON.parse(jsonInput);
      console.log("Valid JSON:", parsedJson);

      // Send the parsed JSON to the backend
      const response = await axios.post("/api/userdata/create", parsedJson);
      console.log("Response:", response.data);

      setMessage("Valid JSON submitted and saved!");

      // Redirect to Page 3
      navigate("/3", { state: { workflowName } });
    } catch (error) {
      console.error("Invalid JSON:", error);
      setMessage("Invalid JSON. Please correct it and try again.");
    }
  };

  return (
    <div>
      <Navbar workflowName={workflowName} />
      <div className="container">
        <div className="format-box">
          <h3>JSON Input Format Example:</h3>
          <pre>
            {`{
  "name": "John Doe",
  "dob": "1990-01-01",
  "gender": "M",
  "pincode": "400001"
}`}
          </pre>
        </div>
        <h2>Input JSON Data</h2>
        <textarea
          value={jsonInput}
          onChange={(e) => setJsonInput(e.target.value)}
          placeholder='Enter JSON in this format -- {"name": "John Doe", "dob": "1990-01-01", "gender": "M", "pincode": "400001"}'
          className="json-input"
        />
        <button onClick={handleSubmit} className="submit-button">
          Submit
        </button>
        {message && <p className="message">{message}</p>}
      </div>
    </div>
  );
};

export default Page2;
