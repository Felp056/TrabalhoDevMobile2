package Talini.trabalhodevmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.savedstate.SavedStateRegistry;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCliente;
    private AutoCompleteTextView autoProduto;
    private  EditText EdQuant;
    private  TextView EdValor;
    private ImageButton imgBtnAddProduto;
    private RadioButton radAVista;
    private RadioButton radParcela;
    private TextView TxPedido;
    private Button btnFinalizar;
    private RadioGroup rgTipoCompra;
    private LinearLayout layParcelas;
    private Spinner spnParcela;
    private ArrayList<ClienteObjeto> lstCliente;
    private ArrayList<ProdutoObjeto> lstProd;
    private ArrayList<PedidoObjeto> lstPedido;
    private  ImageButton imgBtnAddCliente;
    private ImageButton imgBtnConfirmar;
    private AutoCompleteTextView autoId;
    private Button newId;
    private Button btnProcura;
    private Button btnConsultar;
    private String strTexto = "";
    private double ValorTotal = 0;
    Integer[] vetParcelas = {2,3,4,5,6,7,8,9,10};
    int tentativa = 0;
    int parcela = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoCliente = findViewById(R.id.autoCliente);
        autoProduto = findViewById(R.id.autoProduto);
        EdQuant = findViewById(R.id.EdQuant);
        EdValor = findViewById(R.id.EdValor);
        imgBtnAddProduto = findViewById(R.id.imgBtnAddProduto);
        radAVista = findViewById(R.id.radAVista);
        radParcela = findViewById(R.id.radParcela);
        TxPedido = findViewById(R.id.TxPedido);
        btnFinalizar = findViewById(R.id.btnFinalizar);
        rgTipoCompra = findViewById(R.id.rgTipoCompra);
        layParcelas = findViewById(R.id.layParcelas);
        spnParcela = findViewById(R.id.spnParcelas);
        imgBtnAddCliente = findViewById(R.id.imgBtnAddCliente);
        imgBtnConfirmar = findViewById(R.id.imgBtnConfirmar);
        autoId = findViewById(R.id.AutoId);
        newId = findViewById(R.id.btnNew);
        btnProcura = findViewById(R.id.btnProcura);
        btnConsultar = findViewById(R.id.btnConsultar);

        GerarCliente();
        GerarProduto();
        GerarId();
        PopularSpinner();
        rgTipoCompra.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == radParcela.getId()) {
                    layParcelas.setVisibility(View.VISIBLE);
                } else if (i == radAVista.getId()) {
                    layParcelas.setVisibility(View.GONE);
                }
            }
        });
        newId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NovoId();
            }
        });
        imgBtnAddProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(ProdutosActivity2.class);
            }
        });
        imgBtnAddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastroClienteActivity2.class);
            }
        });
        imgBtnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fechaItem();
            }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FecharPedido();

            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivity(NotaImpressaActivity.class);
            }
        });


        btnProcura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstProd = Controler.getInstance().RetornaProduto();
                String[] vetProdutow = new String[lstProd.size()];
                Double[] vetValores = new Double[lstProd.size()];
                for (int i = 0; i < lstProd.size(); i++) {
                    ProdutoObjeto product = lstProd.get(i);
                    vetProdutow[i] = product.getDescricaoProduto();
                    vetValores[i] = product.getValorProduto();
                }
                String valorSelecionado = autoProduto.getText().toString();
                for (int i = 0; i < lstProd.size(); i++) {
                    if (vetProdutow[i].contains(valorSelecionado)) {
                        EdValor.setText(vetValores[i].toString());
                        Toast.makeText(getApplicationContext(), "Valor selecionado: " + valorSelecionado, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
        spnParcela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tentativa = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    private void NovoId() {
        if(lstPedido.size() == 0){
            autoId.setText("1");
        }else {
            autoId.setText(String.valueOf(lstPedido.size() + 1));
        }
    }
    private void FecharPedido() {
        if(autoCliente.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this ,"Deve haver pelo menos um cliente selecionado.", Toast.LENGTH_LONG).show();
            return;
        }
        if(radAVista.isChecked()){
            strTexto += "\nTotal : "+(ValorTotal*0.95)+ "\n";
            TxPedido.setText(strTexto);
            Toast.makeText(MainActivity.this ,"Pedido Salvo", Toast.LENGTH_LONG).show();
            return;
        } else if (radParcela.isChecked()) {
                parcela = vetParcelas[tentativa];
                if(parcela == 0){
                    Toast.makeText(MainActivity.this ,"Deve ser selecionado o valor da parcela", Toast.LENGTH_LONG).show();
                    return;
                }
            Toast.makeText(MainActivity.this ,"Pedido "+autoId.getText().toString()+" Salvo", Toast.LENGTH_LONG).show();
            strTexto += "Total : "+(ValorTotal*1.05)+"\n"+"Quantidade de parcelas: "+parcela+"\n"+"Valor por parcela : "+((ValorTotal*1.05)/parcela)+"\n";
            TxPedido.setText(strTexto);
            PedidoObjeto pedido = new PedidoObjeto();
            pedido.setIdpedido(Integer.parseInt(autoId.getEditableText().toString()));
            pedido.setNota(TxPedido.getText().toString());
            pedido.setClienteNome(autoCliente.getText().toString());
            lstPedido.add(pedido);
            Controler.getInstance().SalvaPedio(pedido);
            LimparCampos();
        }
    }

    private void fechaItem() {
        double Quantidade = 0;
        double valor = 0;
         if(autoProduto.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this ,"Descrição do produto está em branco, preencha-o", Toast.LENGTH_LONG).show();
            return;
        }
        if(EdValor.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this ,"Valor do produto está em branco, preencha-o", Toast.LENGTH_LONG).show();
            return;
        }else{
            try {
                 valor = Double.parseDouble(EdValor.getText().toString());
                if(valor <= 0){
                    throw new Exception("Valor do produto não pode ser igual ou menor que 0");
                }
            }catch (Exception ex){
                Toast.makeText(MainActivity.this ,ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        }
        if(EdQuant.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this ,"Deve ter pelo menos uma unidade do produto posto", Toast.LENGTH_LONG).show();
            return;
        }else{
            try {
                 Quantidade = Double.parseDouble(EdQuant.getText().toString());
                if(Quantidade <= 0){
                    throw new Exception("Quantidade do produto não pode ser igual ou menor que 0");
                }
            }catch (Exception ex){
                Toast.makeText(MainActivity.this ,ex.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }
        }
        ValorTotal += (Quantidade * valor);
        strTexto += autoProduto.getText().toString() + " : "+ valor+"  X  "+Quantidade+"\n";
        TxPedido.setText(strTexto);

    }
    public void LimparCampos(){
        autoProduto.setText("");
        autoCliente.setText("");
        EdQuant.setText("");
        EdValor.setText("");
        TxPedido.setText("");
        NovoId();
    }
    private void GerarCliente () {
        lstCliente = Controler.getInstance().RetornaCliente();
        String[] vetCliente = new String[lstCliente.size()];
        for (int i = 0; lstCliente.size() > i; i++) {
            ClienteObjeto clint = lstCliente.get(i);
            vetCliente[i] = clint.getNome();
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetCliente);
        autoCliente.setAdapter(adapter);
    }

    private void PopularSpinner () {
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetParcelas);
        spnParcela.setAdapter(adapter);
    }
    private void GerarId () {
        lstPedido = Controler.getInstance().RetornaPedio();
        String[] vetPedido = new String[lstPedido.size()];
        for (int i = 0; i < lstPedido.size(); i++) {
            PedidoObjeto pedido = lstPedido.get(i);
            vetPedido[i] = String.valueOf(pedido.getIdpedido());
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetPedido);
        autoId.setAdapter(adapter);
    }

    private void GerarProduto () {
        lstProd = Controler.getInstance().RetornaProduto();
        String[] vetProduto = new String[lstProd.size()];
        for (int i = 0; i < lstProd.size(); i++) {
            ProdutoObjeto product = lstProd.get(i);
            vetProduto[i] = product.getDescricaoProduto();
        }
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line, vetProduto);
        autoProduto.setAdapter(adapter);
    }
    private void abrirActivity(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        GerarCliente();
        GerarProduto();
        GerarId();
    }
}