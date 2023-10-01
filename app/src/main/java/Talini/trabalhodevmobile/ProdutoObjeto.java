package Talini.trabalhodevmobile;

public class ProdutoObjeto {
    private String DescricaoProduto;
    private double ValorProduto;
    private int IdProduto;

    public ProdutoObjeto (){}
    public  ProdutoObjeto(String pDescProd, int pValor, int pId){
        ValorProduto = pValor;
        IdProduto = pId;
        DescricaoProduto = pDescProd;
    }
    public String getDescricaoProduto() {return DescricaoProduto;}

    public void setDescricaoProduto(String descricaoProduto) {DescricaoProduto = descricaoProduto;}

    public double getValorProduto() {
        return ValorProduto;
    }

    public void setValorProduto(double valorProduto) {
        ValorProduto = valorProduto;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int idProduto) {
        IdProduto = idProduto;
    }
}
