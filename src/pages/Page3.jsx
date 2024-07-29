import { useLocation } from "react-router-dom";
import Navbar from "../components/Navbar";
import Workflow from "../components/Workflow";

const Page3 = () => {
  const location = useLocation();
  const workflowName = location.state?.workflowName || "Workflow Name";

  return (
    <div>
      <Navbar workflowName={workflowName} />
      <Workflow />
    </div>
  );
};

export default Page3;
