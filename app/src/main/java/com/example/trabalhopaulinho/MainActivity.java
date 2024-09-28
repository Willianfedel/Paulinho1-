package com.example.trabalhopaulinho;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextNome, editTextEmail, editTextIdade, editTextDisciplina, editTextNota1, editTextNota2;
    TextView textViewResultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSalvar, buttonLimpar;

        // Inicializar os componentes
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        textViewResultado = findViewById(R.id.textViewResultado);

        // Configurar o botão de salvar
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarFormulario();
            }
        });
        // Configurar o botão de limpar
        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewResultado.setText("");
    }

    private void validarFormulario() {
        // Obter os valores dos campos
        String nome = editTextNome.getText().toString();
        String email = editTextEmail.getText().toString();
        String idade = editTextIdade.getText().toString();
        String disciplina = editTextDisciplina.getText().toString();
        String nota1 = editTextNota1.getText().toString();
        String nota2 = editTextNota2.getText().toString();


        StringBuilder erros = new StringBuilder();


        if (TextUtils.isEmpty(email)) {
            erros.append("Email é obrigatório.\n");
        }

        if (TextUtils.isEmpty(idade)) {
            erros.append("Idade é obrigatória.\n");
        } else {
            // Tentar converter a idade para um número
            try {
                int idades = Integer.parseInt(idade);
                if (idades <= 0) {
                    erros.append("Idade deve ser maior que zero.\n");
                }
            } catch (NumberFormatException e) {
                erros.append("Idade deve ser um número válido.\n");
            }
        }

        if (TextUtils.isEmpty(disciplina)) {
            erros.append("Disciplina é obrigatória.\n");
        }

        if (TextUtils.isEmpty(nota1)) {
            erros.append("Nota do 1º Bimestre é obrigatória.\n");
        } else{
            try{
                Double nota1Double = Double.parseDouble(nota1);
                if ( nota1Double < 0 || nota1Double > 10){
                    erros.append("1º Nota Inválida");
                }
            }catch ( NumberFormatException e){
                erros.append("1º Nota Inválida");
            }

        }

        if (TextUtils.isEmpty(nota2)) {
            erros.append("Nota do 2º Bimestre é obrigatória.\n");
        } else{
            try{
                Double nota2Double = Double.parseDouble(nota2);
                if ( nota2Double < 0 || nota2Double > 10){
                    erros.append("2º Nota Inválida");
                }
            }catch ( NumberFormatException e){
                erros.append("2º Nota Inválida");
            }
        }


        if (erros.length() > 0) {
            // Exibir erros no TextView;
            textViewResultado.setText(erros.toString());
        } else {

            // Converter as notas para float
            float nota11 = Float.parseFloat(nota1);
            float nota22 = Float.parseFloat(nota2);

            // Calcular a média
            float media = (nota11 + nota22) / 2;

            // Determinar se o aluno foi aprovado ou reprovado
            String resultado;
            if (media >= 6.0) {
                resultado = "Aprovado";
            } else {
                resultado = "Reprovado";
            }
            String resumo = String.format("Nome: %s\nEmail: %s\nIdade: %s\nDisciplina: %s\nNotas 1º e 2º Bimestres: %s, %s\nMédia: %.2f\nResultado: %s",
                    nome, email, idade, disciplina, nota1, nota2, media, resultado);

            textViewResultado.setText(resumo);

        }
    }
}