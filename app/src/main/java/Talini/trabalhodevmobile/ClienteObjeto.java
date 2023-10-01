package Talini.trabalhodevmobile;

public class ClienteObjeto {
    private String Nome;
    private int Cpf;

    public ClienteObjeto(){}
    public ClienteObjeto (String pNome, int pCpf)
    {
        Nome = pNome;
        Cpf = pCpf;
    }
    public String getNome() {return Nome;}

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getCpf() {
        return Cpf;
    }

    public void setCpf(int cpf) {
        Cpf = cpf;
    }
}
