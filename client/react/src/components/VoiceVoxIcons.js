import React, { useState } from 'react';
import './VoiceVoxIcons.scss'


function VoiceVoxIcons({ selectedNews }) {
    const [selectedIcon, setSelectedIcon] = useState(null);

    const newsItemsMapping = {
        'GENERAL': 0,
        'WORLD': 1,
        'BUSINESS': 2,
        'TECHNOLOGY': 3,
        'ENTERTAINMENT': 4,
        'SPORTS': 5,
        'SCIENCE': 6,
        'HEALTH': 7,
    };

    const handleIconClick = (icon) => {
        if (selectedIcon === icon) {
            setSelectedIcon(null); // 既に選択されたアイコンを再クリックした場合は、選択を解除
        } else {
            setSelectedIcon(icon);
        }
    };

    const handleSummarizeClick = () => {
        const selectedNewsNumbers = selectedNews.map(newsCategory => newsItemsMapping[newsCategory]);
        console.log(selectedNewsNumbers);
    }

    return (
        <div className='icons'>
            <p>喋らせたい声を選んでね</p>
            <img className={selectedIcon === 'spring' ? 'selected' : ''} src="image (4).png" alt="VoiceVox 春日部" onClick={() => handleIconClick('spring')} />
            <img className={selectedIcon === 'zunda' ? 'selected' : ''} src="image (5).png" alt="VoiceVox ずんだもん" onClick={() => handleIconClick('zunda')} />
            <img className={selectedIcon === 'genno' ? 'selected' : ''} src="image (2).png" alt="VoiceVox 玄野" onClick={() => handleIconClick('genno')} />
            <img className={selectedIcon === 'shiro' ? 'selected' : ''} src="image (3).png" alt="VoiceVox 白上" onClick={() => handleIconClick('shiro')} />
            <p>
            <button className="summarize-button" onClick={() => handleSummarizeClick()}>要約する</button>
            </p>
        </div>
    );
}

export default VoiceVoxIcons