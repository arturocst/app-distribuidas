import socket
import threading
import tkinter as tk
from tkinter import ttk

class MarcoServidorUDP(tk.Tk):

    def __init__(self):
        super().__init__()
        self.title("Servidor UDP")
        self.geometry("400x300")
        self.resizable(False, False)

        self.area_texto = tk.Text(self, wrap="word")
        self.area_texto.pack(fill="both", expand=True)

        self.usuarios = {"usuario1": "contrasena1", "usuario2": "contrasena2"}

        threading.Thread(target=self.iniciar_servidor).start()

    def iniciar_servidor(self):
        try:
            servidor_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            servidor_socket.bind(("localhost", 9999))

            while True:
                datos, direccion_cliente = servidor_socket.recvfrom(1024)
                mensaje_recibido = datos.decode()
                partes_mensaje = mensaje_recibido.split(":")
                nombre_usuario = partes_mensaje[0]
                contrasena = partes_mensaje[1]
                mensaje = partes_mensaje[2]

                if nombre_usuario in self.usuarios and self.usuarios[nombre_usuario] == contrasena:
                    self.area_texto.insert(tk.END, f"{nombre_usuario}: {mensaje}\n")
                else:
                    self.area_texto.insert(tk.END, f"Intento de acceso no autorizado desde {direccion_cliente}\n")

        except Exception as e:
            print("Error:", e)

if __name__ == "__main__":
    app = MarcoServidorUDP()
    app.mainloop()
