package Talini.trabalhodevmobile;

import java.util.ArrayList;

public class Controler {
    private static Controler instancia;
    private ArrayList<ClienteObjeto> lstCliente;
    private ArrayList<ProdutoObjeto> lstProduto;
    private ArrayList<PedidoObjeto> lstPedido;

    public static Controler getInstance() {
        if (instancia == null) {
            return instancia = new Controler();
        } else {
            return instancia;
        }
    }
    private Controler() {
        lstCliente = new ArrayList<>();
        lstProduto = new ArrayList<>();
        lstPedido = new ArrayList<>();
    }
    public void salvarCliente (ClienteObjeto clint) {lstCliente.add(clint);}
    public void SalvarProduto (ProdutoObjeto prod) {lstProduto.add(prod);}
    public void SalvaPedio (PedidoObjeto ped) {lstPedido.add(ped);}

    public ArrayList<ClienteObjeto> RetornaCliente () {return lstCliente;}
    public ArrayList<ProdutoObjeto> RetornaProduto () {return  lstProduto;}
    public ArrayList<PedidoObjeto> RetornaPedio () {return  lstPedido;}
}
