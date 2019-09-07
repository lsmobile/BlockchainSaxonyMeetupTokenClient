var express = require('express');
var app = express();

app.use(express.static(__dirname + '/www'));
app.use(express.urlencoded({ extended: false }));

app.listen('8080');
console.log('working on 8080');

// GET redirect root to /frontpage.html
app.get('/', function(req, res){
   res.redirect('/frontpage.html');
});

// POST /frontpage

app.post('/frontpage.html', function (req, res) {

});



