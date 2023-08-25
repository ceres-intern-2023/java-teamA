import React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';
import Container from '@mui/material/Container';
import Typography from '@mui/material/Typography';

export default function NewsList({ selectedNews, setSelectedNews }) {

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
    
    const favicons = {
        'GENERAL': 'https://t2.gstatic.com/images?q=tbn:ANd9GcRf-C90lKnEGnkoAe2ZVEgewlsswvEDCYbSOoi6kbutRtyDso5h',
        'WORLD': 'https://rootsacademy.ma/wp-content/uploads/2022/05/cropped-favicon.png',
        'BUSINESS': 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQRTVuF7bmaXOfohKq9bh1-pdha8URY6w5Smgtpl4cFfpkmOh8a',
        'TECHNOLOGY': 'https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQDk0SBbLu_Fso1KF5nNCLM1mOx9UotN63eL9IncBx8XbrvKrvm',
        'ENTERTAINMENT': 'https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQdTZ3LhfKLl0DJy4MwV7oQJIgzC5vk_Hpr9i3b3Lwv8FoX5EhD',
        'SPORTS': 'https://t1.gstatic.com/images?q=tbn:ANd9GcRz6B_cUCxnWp-vYVlwfHHW4DUet2JlpLp35CAtvP_GEYWGWUYX',
        'SCIENCE': 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQSGsPpSf6XGndzv-XBxTwTtPxgeejVDPJE1PFwmpHMU3rzO-I0',
        'HEALTH': 'https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSRMfqLsRItJewhgRBdUiqfIdj_HFvPnzL3EtAvdqWAiecEv2uD',
    };

    const newsItems = Object.keys(newsItemsMapping);

    const handleItemClick = (item) => {
        if (selectedNews.includes(item)) {
            setSelectedNews(selectedNews.filter(news => news !== item));
        } else {
            setSelectedNews([...selectedNews, item]);
        }
    };

    const selectNewsInt = () => {
        const selectedNewsNumbers = selectedNews.map(newsCategory => newsItemsMapping[newsCategory]);
        console.log(selectedNewsNumbers);
    };


    return (
        <Container sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', mt: 3}}>
            <div className='mainPage'>
                <h2>ニュースカテゴリーを選択してください</h2>
                <List dense sx={{ width: '100%', maxWidth: 1000, bgcolor: 'background.paper', textAlign: 'center' }}>
                    {newsItems.map((item, index) => (
                        <ListItem
                        key={index}
                        disablePadding
                        sx={{
                            boxShadow: selectedNews.includes(item) ? '0 0 0 2px #3f51b5' : 'none',
                            marginTop: 0.1,
                            backgroundColor: selectedNews.includes(item) ? '#f0f0f0' : 'transparent'
                        }}
                    >
                            <ListItemButton onClick={() => handleItemClick(item)}>
                                <ListItemAvatar>
                                    <Avatar
                                        alt={`${item} icon`}
                                        src={favicons[item]}
                                        sx={{ width: 40, height: 40 }} // アイコンのサイズを変更
                                    />
                                </ListItemAvatar>
                                <ListItemText primary={<Typography sx={{ fontSize: '1.0rem', marginLeft: 2, marginRight: 2 }}>{item}</Typography>} />
                            </ListItemButton>
                            <Checkbox
                                edge="end"
                                onChange={() => handleItemClick(item)}
                                checked={selectedNews.includes(item)}
                                inputProps={{ 'aria-labelledby': `checkbox-list-secondary-label-${index}` }}
                                sx={{ marginLeft: 10, marginRight: 2 }}
                            />
                        </ListItem>
                    ))}
                </List>
                <div className='submit-button'>
                    {/* <button onClick={() => setSelectedNews([])}>リセット</button> */}
                    <button onClick={selectNewsInt}>ログに出す</button>
                </div>
            </div>
        </Container>
    );
}