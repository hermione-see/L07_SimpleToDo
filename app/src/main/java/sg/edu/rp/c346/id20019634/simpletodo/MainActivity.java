package sg.edu.rp.c346.id20019634.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTasks;
    ArrayList<String> alTasks;
    Spinner spnAddDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTasks = findViewById(R.id.listViewTasks);
        spnAddDelete = findViewById(R.id.spinner);

        alTasks = new ArrayList<String>();

        ArrayAdapter aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);
        lvTasks.setAdapter(aaTasks);

        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        break;

                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newTask = etTask.getText().toString();
                alTasks.add(newTask);
                aaTasks.notifyDataSetChanged();
                etTask.setText(null);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (alTasks.isEmpty() == true) {

                    Toast.makeText(MainActivity.this,"You don't have any task to remove.", Toast.LENGTH_SHORT).show();

                } else {

                    int index = Integer.parseInt(etTask.getText().toString());

                    if (index >= alTasks.size()) {

                        Toast.makeText(MainActivity.this,"Wrong index number.", Toast.LENGTH_SHORT).show();

                    } else {

                        alTasks.remove(index);
                        aaTasks.notifyDataSetChanged();

                    }

                }


            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alTasks.clear();
                aaTasks.notifyDataSetChanged();

            }
        });

    }
}