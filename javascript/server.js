//Socket server Javascript

var net = require('net');
const readline = require('readline-sync')

const server = net.createServer()

console.log("Buscando cliente...")

server.on('connection',(socket)=>{
    socket.on('data', data =>{
        console.log(""+ data)
        sendLine()
    })

    console.log("(Ciente conectado con exito)")

    socket.on('close',()=>{
        console.log("Comunicacion finalizada")
    })
    socket.on('error', (err)=>{
        console.log(err.message)
    })
    function sendLine(){
        var line = readline.question('Server: ')  
        if (line == "0"){
            socket.end()
        }else{
            socket.write("Server: " + line)
        }
    
    }

})

const port = 5020

server.listen(port, ()=>{
    console.log("Numero de puerto", server.address().port)
})