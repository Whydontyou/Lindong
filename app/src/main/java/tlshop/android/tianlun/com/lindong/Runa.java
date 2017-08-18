package tlshop.android.tianlun.com.lindong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Runa extends AppCompatActivity {
private Button but1,but2,but3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runa);
        but1= (Button) findViewById(R.id.but1);
        but2= (Button) findViewById(R.id.but2);
        but3= (Button) findViewById(R.id.but3);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Runa.this,TableActivity.class
                ).putExtra("name","1"));
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Runa.this,TableActivity.class
                ).putExtra("name","2"));
            }
        });
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Runa.this,TableActivity.class
                ).putExtra("name","3"));
            }
        });
    }
}
