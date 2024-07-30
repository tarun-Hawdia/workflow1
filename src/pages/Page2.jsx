import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "../styles/Page2.css";
import Navbar from "../components/Navbar";
import axios from "axios";

const Page2 = () => {
  const [jsonInput, setJsonInput] = useState("");
  const [message, setMessage] = useState("");
  const [workflowData, setWorkflowData] = useState([]);
  const navigate = useNavigate();
  const location = useLocation();
  const workflowName = location.state?.workflowName || "Workflow Name";

  const handleSubmit = async () => {
    try {
      const parsedJson = JSON.parse(jsonInput);
      console.log("Valid JSON:", parsedJson);

      // Send the parsed JSON to the backend
      const response = await axios.post(
        "http://localhost:8080/api/userdata/create",
        parsedJson
      );
      console.log("Response:", response.data);

      setMessage("Valid JSON submitted and saved!");

      // Redirect to Page 3
      navigate("/3", { state: { workflowName } });
    } catch (error) {
      console.error("Invalid JSON:", error);
      setMessage("Invalid JSON. Please correct it and try again.");
    }
  };

  const handleSelect = (workflow) => {
    // Handle the selection of the workflow here
    console.log("Selected Workflow:", workflow);
    // You can set the selected workflow in state or navigate to another page
  };

  useEffect(() => {
    const fetchWorkflowData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/workflows/summary"
        );
        setWorkflowData(response.data);
      } catch (error) {
        console.error("Error fetching workflow data:", error);
      }
    };

    fetchWorkflowData();
  }, []);

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
        <h2>Available Workflows</h2>
        <div className="workflow-grid">
          {workflowData.length > 0 ? (
            workflowData.map((workflow, index) => (
              <div key={index} className="workflow-box">
                <h4>{workflow.name}</h4>
                <button
                  onClick={() => handleSelect(workflow)}
                  className="select-button"
                >
                  Select
                </button>
              </div>
            ))
          ) : (
            <p>No data available</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default Page2;
