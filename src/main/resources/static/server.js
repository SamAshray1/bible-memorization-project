const express = require('express');
const cors = require('cors');
const app = express();

const PORT = 3000;

app.use(cors());

app.listen(PORT, () => {
  console.log(`Proxy server is running on port ${PORT}`);
});

app.get('/get-verse/:book/:chapter/:verse', (req, res) => {
  const { book, chapter, verse } = req.params;
  // Replace this with your actual API endpoint logic
  res.json({ book, chapter, verse, content: 'Your verse content goes here.' });
  //console.log(res);
});
