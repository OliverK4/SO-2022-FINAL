//Socket cliente Javascript

var net = require('net');
const readline = require('readline-sync')
const options = {
    port : 5020,
    host: 'localhost'
}
const client = net.createConnection(options)

console.log('Buscando servidor...')

client.on('connect',()=>{
    console.log('(Conectado al servidor con exito)')
    sendLine()
})

client.on('error',(err)=>{
    console.log(err.message)
})
client.on('data',(data)=>{
    console.log(''+ data)
    sendLine()
})

function sendLine(){
    var line = readline.question('Cliente: ')
    if (line == "0"){
        client.end()
    }else{
        client.write("Cliente: " + line)
    }
}