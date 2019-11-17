import React, { Component } from "react";
import Header from "./components/Layout/Header";
import Dashboard from "./components/Dashboard";
import { BrowserRouter, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import UpdateProject from "./components/Project/UpdateProject";

import { Provider } from "react-redux";
import store from "./Store";

class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <BrowserRouter>
          {" "}
          <Header />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route
            exact
            path="/updateProject/:projectIdentifier"
            component={UpdateProject}
          />
        </BrowserRouter>
      </Provider>
    );
  }
}

export default App;