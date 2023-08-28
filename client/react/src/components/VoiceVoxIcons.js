import React, { useState } from 'react';
import axios from 'axios';
import Textarea from '@mui/joy/Textarea';
import './VoiceVoxIcons.scss'


function VoiceVoxIcons({ selectedNews }) {
    const [selectedIcon, setSelectedIcon] = useState(null);
    const [newsText, setNewsText] = useState("");

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
        axios.post('http://localhost:8080/api/news', {
            ids: selectedNewsNumbers
        })
            .then(function (response) {
                console.log(response.data);
                setNewsText(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    const handleCreateVoice = async () => {
        console.log("YO!");
        let url = `https://deprecatedapis.tts.quest/v2/voicevox/audio/?key=m76-4830047223w&text=(${newsText})&speaker=4`
        try {
            const AUDIO_ELEMENT = document.querySelector("#audio");
            const response = await fetch(url);
            const blob = await response.blob(); // ここで修正
            const url_1 = URL.createObjectURL(blob);
            AUDIO_ELEMENT.src = url_1;
            return AUDIO_ELEMENT.play();
        } catch (error) {
            return console.error(error);
        }
        // axios.get(url)
        //     .then(async (res) => {
        //         console.log("hoge!");
        //         console.log(res);
        //         const AUDIO_ELEMENT = document.querySelector("#audio");
        //         const url = URL.createObjectURL(await res.data.blob());
        //         AUDIO_ELEMENT.src = res.data;
        //         AUDIO_ELEMENT.play();

        //     })
        //     .catch((e) => {
        //         console.error(e);
        //     })
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
            <Textarea
                value={newsText}
                inputProps={{ readonly: true }}
                sx={{ m: 2, mt: 12, mx: 20 }}
            />
            <button className="summarize-button" onClick={() => handleCreateVoice()}>読み上げる</button>
            <div style={{ margin: "20px" }}>
                <audio controls loop id="audio">
                </audio>
            </div>
        </div >
    );
}

export default VoiceVoxIcons