import React, { useState } from 'react';
import './App.css';
import Header from './components/Header';
import NewsList from './components/NewsList';
import VoiceVoxIcons from './components/VoiceVoxIcons';
import ResPage from './components/ResPage';

function App() {
  const [selectedNews, setSelectedNews] = useState([]);

  return (
    <div className="App">
      <Header />
      <NewsList selectedNews={selectedNews} setSelectedNews={setSelectedNews} />
      <VoiceVoxIcons selectedNews={selectedNews} />
      {/* <ResPage /> */}
    </div>
  );
}

export default App;