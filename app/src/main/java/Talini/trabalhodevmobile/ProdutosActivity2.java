package Talini.trabalhodevmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ProdutosActivity2 extends AppCompatActivity {

    private EditText IdProduto;
    private  EditText DescricaoProd;
    private  EditText EdValor;
    private ImageButton btnSalvarProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos2);
        IdProduto = findViewById(R.id.EdIdproduto);
        DescricaoProd = findViewById(R.id.EdDescricaoProduto);
        EdValor = findViewById(R.id.EdValorCadastro);
        btnSalvarProd = findViewById(R.id.imgSalvarPedido);

        btnSalvarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SalvarProduto();
            }
        });
    }

    private void SalvarProduto() {
        ProdutoObjeto prod = new ProdutoObjeto();
        int id = Integer.parseInt(IdProduto.getText().toString().trim());
        if(id < 0){
            Toast.makeText(ProdutosActivity2.this ,"Não é permitido Id negatico", Toast.LENGTH_LONG).show();
            return;
        }
        if(DescricaoProd.getText().toString().trim() == null){
            Toast.makeText(ProdutosActivity2.this ,"Não é permitido deixar a descrição em branco", Toast.LENGTH_LONG).show();
            return;
        }
        double valor = Double.parseDouble(EdValor.getText().toString().trim());
        if(valor <= 0){
            Toast.makeText(ProdutosActivity2.this ,"Não exite produto de valor 0 ou menor", Toast.LENGTH_LONG).show();
            return;
        }
        prod.setIdProduto(id);
        prod.setValorProduto(valor);
        prod.setDescricaoProduto(DescricaoProd.getText().toString().trim());
        Controler.getInstance().SalvarProduto(prod);
        Toast.makeText(ProdutosActivity2.this ,"Cadastrado produto com sucesso", Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}