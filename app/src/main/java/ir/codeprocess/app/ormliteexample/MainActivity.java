package ir.codeprocess.app.ormliteexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ir.codeprocess.app.database.DBHelper;
import ir.codeprocess.app.database.Student;

public class MainActivity extends AppCompatActivity {

    Button btnAddNewStudent, btnGetAllStudents;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNewStudent = findViewById(R.id.btnAddNewStudent);
        btnGetAllStudents = findViewById(R.id.btnGetAllStudents);

        dbHelper = new DBHelper(this);

        btnAddNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student student = new Student();
                student.setFirstName("Masoud");
                student.setLastName("Torabi");
                student.setMobileNumber("09194870741");
                student.setAddress("Karaj");

                try {
                    dbHelper.createOrUpdate(student);
                    Toast.makeText(getApplicationContext(), "NEW STUDENT ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        btnGetAllStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List mStudentList = new ArrayList();
                try {
                    mStudentList.addAll(dbHelper.getAll(Student.class));
                    Toast.makeText(getApplicationContext(), "STUDENT COUNT IN DATABASE = " + mStudentList.size(), Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
