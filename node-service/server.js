let http = require('http');
let url = require('url');
let path = require('path');

let server = http.createServer(function (req, res) {
    //获得请求的路径
    let pathname = url.parse(req.url).pathname;
    res.writeHead(200, {'Content-Type': 'application/json; charset=utf-8'});
    //访问http://localhost:8060/,返回{"index": "欢迎来到首页"}
    if (pathname === '/'){
        res.end(JSON.stringify({"index": "欢迎来到首页"}));
    }
    //访问http://localhost:8060/health,会返回{"status": "UP"}
    else if (pathname === "/health"){
        res.end(JSON.stringify({"status": "UP"}));
    }
    //其他情况返回404
    else {
        res.end("404");
    }
});

//创建监听，并打印日志
server.listen(8060, function () {
    console.log('listening on localhost:8060');
});