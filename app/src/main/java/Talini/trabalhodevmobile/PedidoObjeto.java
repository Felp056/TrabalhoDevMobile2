package Talini.trabalhodevmobile;

public class PedidoObjeto {
    private  int Idpedido;
    private String ClienteNome;
    private String Nota;

    public String getClienteNome() {
        return ClienteNome;
    }

    public void setClienteNome(String clienteNome) {
        ClienteNome = clienteNome;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }
    public int getIdpedido() {
        return Idpedido;
    }

    public void setIdpedido(int idpedido) {
        Idpedido = idpedido;
    }
}
