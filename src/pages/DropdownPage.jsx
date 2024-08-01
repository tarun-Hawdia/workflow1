// src/components/DropdownPage.js
import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../styles/DropdownPage.css";

const options = ["Age", "Gender", "Pincode"];
const loanStatusOption = "Loan Status";

const DropdownPage = ({ setWorkflowName }) => {
  const [workflowNameInput, setWorkflowNameInput] = useState("");
  const [dropdowns, setDropdowns] = useState([{ option: "", text: "" }]);
  const [loanStatus, setLoanStatus] = useState("");
  const navigate = useNavigate();

  const handleOptionChange = (index, value) => {
    const newDropdowns = [...dropdowns];
    newDropdowns[index].option = value;

    // Update input field value and type
    if (index < dropdowns.length - 1) {
      // Ensure subsequent dropdowns are cleared or updated correctly
      for (let i = index + 1; i < dropdowns.length; i++) {
        newDropdowns[i] = { option: "", text: "" };
      }
    }

    // Update text field type based on new option
    newDropdowns[index].text = ""; // Clear text field value on option change
    setDropdowns(newDropdowns);

    if (index === dropdowns.length - 1 && dropdowns.length < 3) {
      // Limit to 3 rows
      setDropdowns([...newDropdowns, { option: "", text: "" }]);
    }
  };

  const handleTextChange = (index, value) => {
    const newDropdowns = [...dropdowns];
    newDropdowns[index].text = value;
    setDropdowns(newDropdowns);
  };

  const getInputType = (option) => {
    if (option === "Gender") return "text";
    return "number";
  };

  const handleReset = () => {
    setWorkflowNameInput("");
    setDropdowns([{ option: "", text: "" }]);
    setLoanStatus("");
  };

  const handleSubmit = async () => {
    // Prepare the workflow data to be sent to the backend
    const workflowData = {
      name: workflowNameInput,
      steps: dropdowns.map((dropdown) => ({
        task: { name: dropdown.option },
        taskCondition: { conditionText: dropdown.text },
        subTask: { detail: loanStatus },
      })),
    };

    try {
      const response = await axios.post("/api/workflows/create", workflowData);
      console.log("Workflow created:", response.data);
      setWorkflowName(workflowNameInput);
      navigate("/2", { state: { workflowName: workflowNameInput } });
    } catch (error) {
      console.error("Error creating workflow:", error);
    }
  };

  // Determine if all fields are selected and loan status is set
  const allFieldsSelected =
    dropdowns.every((d) => d.option !== "" && d.text !== "") &&
    loanStatus !== "";

  // Get available options by filtering out already selected options
  const getAvailableOptions = (index) => {
    const selectedOptions = dropdowns.slice(0, index).map((d) => d.option);
    return options.filter((option) => !selectedOptions.includes(option));
  };

  return (
    <div className="dropdown-page">
      <h1>Workflow</h1>
      <div className="workflow-name-row">
        <input
          type="text"
          value={workflowNameInput}
          onChange={(e) => setWorkflowNameInput(e.target.value)}
          placeholder="Enter workflow name"
        />
      </div>
      {dropdowns.map((dropdown, index) => (
        <div key={index} className="dropdown-row">
          <select
            value={dropdown.option}
            onChange={(e) => handleOptionChange(index, e.target.value)}
          >
            <option value="" disabled>
              Select option
            </option>
            {getAvailableOptions(index).map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
          </select>
          <input
            type={getInputType(dropdown.option)}
            value={dropdown.text}
            onChange={(e) => handleTextChange(index, e.target.value)}
            placeholder={`Enter ${dropdown.option.toLowerCase()}`}
          />
          {index < dropdowns.length - 1 && (
            <select
              value={dropdowns[index + 1]?.option || ""}
              onChange={(e) => handleOptionChange(index + 1, e.target.value)}
              disabled={!dropdown.option}
            >
              <option value="" disabled>
                Select option
              </option>
              {getAvailableOptions(index + 1).map((option) => (
                <option key={option} value={option}>
                  {option}
                </option>
              ))}
            </select>
          )}
        </div>
      ))}
      {dropdowns.length === 3 &&
        dropdowns.every((d) => d.option !== "" && d.text !== "") && (
          <div className="dropdown-row">
            <select
              value={loanStatus}
              onChange={(e) => setLoanStatus(e.target.value)}
            >
              <option value="" disabled>
                Loan Status
              </option>
              <option value={loanStatusOption}>{loanStatusOption}</option>
            </select>
          </div>
        )}
      <button onClick={handleReset} className="reset-button">
        Reset
      </button>
      <button
        onClick={handleSubmit}
        className={`submit-button ${allFieldsSelected ? "active" : "inactive"}`}
      >
        Submit
      </button>
    </div>
  );
};

export default DropdownPage;
