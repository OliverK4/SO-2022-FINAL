const net = require('net');
const readline = require('readline-sync')

const options = {
    port: 5020,
    host: '127.0.0.1'
}

const client = net.createConnection(options)

client.on('connect', ()=>{
    console.log('Conexión establecida')
    sendLine()
})

client.on('data', (data)=>{
    console.log('Servidor: ' + data)
    sendLine()
})

client.on('error', (err)=>{
    console.log(err.message)
})

function sendLine() {
    var line = readline.question('\nCliente: ')
    if (line == "0") {
        client.end()
    }else{
        client.write(line + "\n")
    }
}