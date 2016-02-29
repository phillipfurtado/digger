var express = require('express');
var bodyParser = require('body-parser'); // pull information from HTML POST (express4)
var methodOverride = require('method-override'); // simulate DELETE and PUT (express4)
var expressWinston = require('express-winston'); // log requests to the console (express4)
var request = require('request');

var domain = require('domain');
var d = domain.create();
var app = express();

app.use(express.static(require('path').resolve(__dirname + "/../client")));

app.use('/server', function(req, res) {
	console.log("Receiving and Proxing request: " + req.url + " - " + req.method.toLowerCase());
	
	var method = req.method.toLowerCase();
	if(method == 'delete') {
		method = 'del';
	}
	
	var newreq = request[method]( (process.env.DIGGER_APPSERVER || 'http://localhost:8080') + '/digger/api' + req.url );
	newreq.on('error', function(e) {
		console.error("Error connecting to Wildfly service - Service Unavailable", e);
		res.writeHead(500, {"Content-Type": "application/json"});
		var responseJSON = { causedBy: "Servidor indisponivel.", errorType: "danger" };
		res.end(JSON.stringify(responseJSON));
	});
	req.pipe(newreq)
			    .pipe(res)
}); // mount the sub app

//parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({'extended':'true'}));
//parse application/json
app.use(bodyParser.json());
//parse application/vnd.api+json as json
app.use(bodyParser.json({ type: 'application/vnd.api+json' }));
//log every request to the console
app.use(methodOverride());

app.get('/', function(req, res) {
    var options = {
      root: __dirname + "/../",
      dotfiles: 'deny',
      headers: {
          'x-timestamp': Date.now(),
          'x-sent': true
      }
    };

    res.sendFile('client/index.html', options, function(err){
        if (err) {
          res.status(500).end();
        }
    });

});

d.on('error', function(err) {
	 console.log(err);
 // Our handler should deal with the error in an appropriate way
});

process.on('uncaughtException', function (err) {
    console.log(err);
});

// listen (start app with node server.js)
app.listen(process.env.SERVER_PORT || 9000);
console.info("Digger WEB listening on port 9000");