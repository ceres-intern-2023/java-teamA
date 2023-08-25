import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './ResPage.scss';
import PlayArrowIcon from '@mui/icons-material/PlayArrow';

function ResPage() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        // APIからデータを取得
        axios.get('http://localhost:8080/sample/users')
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                console.error('データの取得に失敗:', error);
            });
    }, []); // 空の依存配列を渡して、コンポーネントのマウント時にのみ実行

    return (
        <div className='res-page'>
            <h1>ResPage</h1>
            <div className='summarize-text'>こに要約結果が表示されるよjhdfhjkdsfhdjkfshdjkshdsfkjlhfdkjl]
                fjsadfhfldkjhfdsjklhf
                dskjdfhskjlhfdksjl
                fdsjfdsajkhdfkjlhdfsjhskdaffskdljhsklafdhjsdkfhfsdklhdfshfdsklhfhkfshksaldhfdalshdfahfdskldfhlkjsfhdkslahflksjfhjdksfhdksjhksdfhkjdfhskjsdfhjldfshfdsjkhflkashdflkjsfhvbjvbjfgiurgiudsfjkhfdsjkhdfsluiylsfgjhsfdlurawuigfdsuigf
            </div>
            <button className="read-button"><PlayArrowIcon  /></button>
        </div>
    );
}

export default ResPage;
