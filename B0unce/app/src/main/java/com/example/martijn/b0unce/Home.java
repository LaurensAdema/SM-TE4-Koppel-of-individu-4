package com.example.martijn.b0unce;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.martijn.b0unce.model.Map;
import com.example.martijn.b0unce.model.MapObject;
import com.example.martijn.b0unce.model.ball.Ball;
import com.example.martijn.b0unce.model.ball.Circle;
import com.example.martijn.b0unce.model.resources.GamePoint;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TableLayout content = (TableLayout) findViewById(R.id.game);

        for(int row=0;row<10;row++) {
            TableRow tablerow = new TableRow(this);

            tablerow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            if ((row == 2 || row == 4 || row == 7)) {

                for(int col=0;col<4;col++) {
                    TableRow tablecolumn = new TableRow(this);
                    TableRow.LayoutParams param = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
                    tablecolumn.setPadding(2, 2, 2, 2);
                    //param.span = 10;
                    tablecolumn.setLayoutParams(param);
                    TextView tv = new TextView(this);
                    tv.setText("");
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));

                    tv.setBackgroundColor(0xff1a1a1a);
                    tablecolumn.addView(tv);

                    tablerow.addView(tablecolumn);
                }

                TableRow tablecolumn = new TableRow(this);
                TableRow.LayoutParams param = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f);
                param.span = 6;
                tablecolumn.setLayoutParams(param);
                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(5,5,5,5);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundColor(0xff0e0a12);
                tablecolumn.addView(tv);

                if(row==2) {
                    tv.setText("Play");

                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent level = new Intent(Home.this, Game.class);
                            level.putExtra("level", new Map(null, 0, 5, "Test", new HashMap<GamePoint, MapObject>(), new Ball(new GamePoint(20,20), 1, new Circle())));
                            startActivity(level);
                        }
                    });

                } else if(row==4) {
                    tv.setText("10 Man Run");
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder alertbox = new AlertDialog.Builder(Home.this);
                            alertbox.setMessage("Function not available yet.");
                            alertbox.setIcon(android.R.drawable.ic_dialog_alert);
                            alertbox.show();
                        }
                    });
                } else if(row==7) {
                    tv.setText("Level creator");

                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder alertbox = new AlertDialog.Builder(Home.this);
                            alertbox.setMessage("Function not available yet.");
                            alertbox.setIcon(android.R.drawable.ic_dialog_alert);
                            alertbox.show();
                        }
                    });
                }

                tablerow.addView(tablecolumn, 2);

            } else {
                for (int col = 0; col < 10; col++) {
                    TableRow tablecolumn = new TableRow(this);

                    tablecolumn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                    tablecolumn.setPadding(2, 2, 2, 2);

                    TextView tv = new TextView(this);
                    tv.setText("");
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));



                    tv.setBackgroundColor(0xff1a1a1a);

                    tablecolumn.addView(tv);

                    tablerow.addView(tablecolumn);
                }
            }
            content.addView(tablerow);

        }
    }

    public void startGame(View v)
    {
        startActivity(new Intent(Home.this, Game.class));
    }

    public void startHome(View v)
    {
        startActivity(new Intent(Home.this, Home.class));
    }
}
