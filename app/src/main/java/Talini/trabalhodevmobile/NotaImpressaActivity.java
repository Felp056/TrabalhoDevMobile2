package Talini.trabalhodevmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class NotaImpressaActivity extends AppCompatActivity {
private TextView TxNotaProduto;
private Button btnVoltar;
private Button btnRegatarCompra;
private EditText EditId;
    private ArrayList<PedidoObjeto> lstPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_impressa);
        TxNotaProduto = findViewById(R.id.TxNotaProdutos);
        btnVoltar = findViewById(R.id.btnVoltar);
        btnRegatarCompra = findViewById(R.id.btnRegatarCompra);
        EditId = findViewById(R.id.EditId);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegatarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imprimirNOta(Integer.parseInt(EditId.getText().toString()));
            }
        });
    }

    public void imprimirNOta(int pId){
        TxNotaProduto.setText("");
        lstPedido = Controler.getInstance().RetornaPedio();
        String[] vetNota = new String[lstPedido.size()];
        for (int i = 0; i < lstPedido.size(); i++) {
            PedidoObjeto pedido = lstPedido.get(i);
            vetNota[i] = "Id : "+pedido.getIdpedido()+"\nCliente: "+pedido.getClienteNome()+"\n"+pedido.getNota();
        }
        TxNotaProduto.setText(vetNota[pId]);
    }
}