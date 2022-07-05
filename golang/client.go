//Socket cliente Golang

package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
)

func main() {
	fmt.Println("Buscando servidor...")
	var port string = "5020"
	conn, _ := net.Dial("tcp", "127.0.0.1:"+port)
	var nombre string = "Cliente: "
	fmt.Println("(Conectado al servidor con exito)")

	for {
		reader := bufio.NewReader(os.Stdin)
		fmt.Print("Cliente: ")
		text, _ := reader.ReadString('\n')
		fmt.Fprintf(conn, nombre+text)
		message, _ := bufio.NewReader(conn).ReadString('\n')
		fmt.Print(message)
	}
}
