package Talini.trabalhodevmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CadastroClienteActivity2 extends AppCompatActivity {

    private EditText Nome;
    private EditText CPF;
    private ImageButton salvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente2);
        Nome = findViewById(R.id.EdClienteNome);
        CPF = findViewById(R.id.EdClienteCpf);
        salvar = findViewById(R.id.imgSalvarPedido);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salvar_onClick();
            }
        });
    }

    private void Salvar_onClick() {
        ClienteObjeto clint = new ClienteObjeto();
        int cpf;
        if(Nome.getText().toString().trim().isEmpty()){
            Toast.makeText(CadastroClienteActivity2.this ,"Nome está em branco, preencha-o", Toast.LENGTH_LONG).show();
            return;
        }
        if (CPF.getText().toString().trim().isEmpty()) {
            Toast.makeText(CadastroClienteActivity2.this ,"Valor do CPF não pode ser branco", Toast.LENGTH_LONG).show();
            return;
        } else {
            try{
                cpf =Integer.parseInt(CPF.getText().toString().trim());
                if(cpf < 0){
                    Toast.makeText(CadastroClienteActivity2.this ,"Valor do CPF incorreto, apenas numeros maiores que zero são pemitidos", Toast.LENGTH_LONG).show();
                    return;
                }
            }catch (Exception ex){
                Toast.makeText(CadastroClienteActivity2.this ,"Valor do CPF incorreto, apenas numeros são pemitidos", Toast.LENGTH_LONG).show();
                return;
            }
        }
        clint.setCpf(cpf);
        clint.setNome(Nome.getText().toString().trim());
        Controler.getInstance().salvarCliente(clint);
        Toast.makeText(CadastroClienteActivity2.this ,"Salvo com sucesso", Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}