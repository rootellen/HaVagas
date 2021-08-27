package br.edu.ifsp.havagas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.havagas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.radioButtonMasc.setChecked(true);

        activityMainBinding.switchCel.setOnClickListener(v -> {
            //Celular
            if(activityMainBinding.switchCel.isChecked()){
                activityMainBinding.txtCelular.setVisibility(View.VISIBLE);
            }   else{
                activityMainBinding.txtCelular.setVisibility(View.GONE);
            }
        });

        activityMainBinding.spinnerFormacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (((TextView) view).getText().equals("Fundamental") || ((TextView) view).getText().equals("Ensino Medio")){

                    activityMainBinding.editTextAnoConclusao.setVisibility(View.GONE);
                    activityMainBinding.editTextAnoFormatura.setVisibility(View.GONE);
                    activityMainBinding.editTextMonografia.setVisibility(View.GONE);
                    activityMainBinding.editTextOrientador.setVisibility(View.GONE);

                    activityMainBinding.editTextAnoFormatura.setVisibility(View.VISIBLE);
                }   else if(((TextView) view).getText().equals("Graduação") || ((TextView) view).getText().equals("Especialização")){

                    activityMainBinding.editTextAnoFormatura.setVisibility(View.GONE);
                    activityMainBinding.editTextMonografia.setVisibility(View.GONE);
                    activityMainBinding.editTextOrientador.setVisibility(View.GONE);

                    activityMainBinding.editTextAnoConclusao.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextInstituicao.setVisibility(View.VISIBLE);
                }   else if(((TextView) view).getText().equals("Mestrado") || ((TextView) view).getText().equals("Doutorado")){

                    activityMainBinding.editTextAnoFormatura.setVisibility(View.GONE);
                    activityMainBinding.editTextAnoConclusao.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextInstituicao.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextMonografia.setVisibility(View.VISIBLE);
                    activityMainBinding.editTextOrientador.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickButton(View view){
        switch(view.getId()) {
            case R.id.btnSalvar:
                saveForm();
                break;
            case R.id.btnLimpar:
                cleanForm();
                break;
            default:
                break;
        }
    }

    private void saveForm() {
        StringBuffer sumarioSb = new StringBuffer();
        sumarioSb.append("Nome completo: ").append(activityMainBinding.txtNome.getText().toString()).append("\n");
        sumarioSb.append("E-mail: ").append(activityMainBinding.txtEmail.getText().toString()).append("\n");

        if(activityMainBinding.CBemail.isChecked()){
            sumarioSb.append("Receber Emails: ").append("true").append("\n");
        }   else{
            sumarioSb.append("Receber Emails: ").append("false").append("\n");
        }

        sumarioSb.append("Telefone: ").append(activityMainBinding.txtTelefone.getText().toString()).append("\n");

        if(activityMainBinding.CBComercial.isChecked()){
            sumarioSb.append("Telefone comercial: ").append("true").append("\n");
        }   else{
            sumarioSb.append("Telefone comercial: ").append("false").append("\n");
        }

        if(activityMainBinding.switchCel.isChecked()){
            sumarioSb.append("Celular: ").append(activityMainBinding.txtCelular.getText().toString()).append("\n");
        }

        sumarioSb.append("Sexo: ");
        switch (activityMainBinding.radioGroupSexo.getCheckedRadioButtonId()) {
            case R.id.radioButtonMasc:
                sumarioSb.append("masculino");
                break;
            case R.id.radioButtonFem:
                sumarioSb.append("feminino");
                break;
            case R.id.radioButtonOutro:
                sumarioSb.append("outro");
                break;
        }
        sumarioSb.append("\n");

        sumarioSb.append("Data de Nascimento: ").append(activityMainBinding.editTextDate.getText().toString()).append("\n");

        sumarioSb.append("Formacao: ").append(((TextView) activityMainBinding.spinnerFormacao.getSelectedView()).getText()).append("\n");

        if (activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 0 || activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 1){
            sumarioSb.append("Ano de Formatura: ").append(activityMainBinding.editTextAnoFormatura.getText().toString()).append("\n");
        }else if (activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 2 || activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 3){
            sumarioSb.append("Ano de Conclusão: ").append(activityMainBinding.editTextAnoConclusao.getText().toString()).append("\n");
            sumarioSb.append("Instituição: ").append(activityMainBinding.editTextInstituicao.getText().toString()).append("\n");
        }else if (activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 4 || activityMainBinding.spinnerFormacao.getSelectedItemPosition() == 5){
            sumarioSb.append("Ano de Conclusão: ").append(activityMainBinding.editTextAnoConclusao.getText().toString()).append("\n");
            sumarioSb.append("Instituição: ").append(activityMainBinding.editTextInstituicao.getText().toString()).append("\n");
            sumarioSb.append("Título da Monografia: ").append(activityMainBinding.editTextMonografia.getText().toString()).append("\n");
            sumarioSb.append("Orientador: ").append(activityMainBinding.editTextOrientador.getText().toString()).append("\n");
        }

        sumarioSb.append("Vagas de interesse: ").append(activityMainBinding.editTextVagasInteresse.getText().toString()).append("\n");

        Toast.makeText(this, sumarioSb.toString(), Toast.LENGTH_SHORT).show();
    }

    private void cleanForm() {
        activityMainBinding.txtNome.setText("");
        activityMainBinding.txtEmail.setText("");
        activityMainBinding.txtTelefone.setText("");
        activityMainBinding.editTextAnoFormatura.setText("");
        activityMainBinding.editTextAnoConclusao.setText("");
        activityMainBinding.editTextMonografia.setText("");
        activityMainBinding.editTextOrientador.setText("");
        activityMainBinding.editTextInstituicao.setText("");
        activityMainBinding.txtCelular.setText("");
        activityMainBinding.editTextDate.setText("");
        activityMainBinding.editTextVagasInteresse.setText("");
        activityMainBinding.radioButtonMasc.setChecked(true);
        activityMainBinding.spinnerFormacao.setSelection(0);
        activityMainBinding.CBemail.setChecked(false);
        activityMainBinding.CBComercial.setChecked(false);
        activityMainBinding.switchCel.setChecked(false);
    }
}