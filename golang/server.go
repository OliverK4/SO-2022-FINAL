//Socket server Golang

package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
)

func main() {
	fmt.Println("Corriendo servidor GO...")
	var port string = "5020"
	ln, _ := net.Listen("tcp", ":"+port)
	fmt.Println("Buscando cliente...")
	var nombre string = "Server: "
	conn, _ := ln.Accept()
	fmt.Println("(cliente conectado con exito)")

	for {

		message, _ := bufio.NewReader(conn).ReadString('\n')
		fmt.Print(string(message))
		reader := bufio.NewReader(os.Stdin)
		fmt.Print("Server: ")
		text, _ := reader.ReadString('\n')
		fmt.Fprintf(conn, nombre+text)

	}
}
