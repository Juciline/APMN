package ao.co.isptec.aplm.exercise_3;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<String> tarefas = new ArrayList<>();
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText campo = findViewById(R.id.edit_tarefa);
        ListView lista = findViewById(R.id.lista_tarefas);

        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        lista.setAdapter(adaptador);

        // Tecla ENTER (teclado fisico ou evento de tecla)
        campo.setOnKeyListener((view, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && keyCode == KeyEvent.KEYCODE_ENTER) {
                adicionarTarefa(campo);
                return true;                        // evento consumido
            }
            return false;
        });

        // Botao "Concluido" do teclado virtual
        campo.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                adicionarTarefa(campo);
                return true;
            }
            return false;
        });
    }

    private void adicionarTarefa(EditText campo) {
        String texto = campo.getText().toString().trim();
        if (!texto.isEmpty()) {
            tarefas.add(0, texto);                  // novo item no topo
            adaptador.notifyDataSetChanged();
            campo.setText("");
        }
    }
}
