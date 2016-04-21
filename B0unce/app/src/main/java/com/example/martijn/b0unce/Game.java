package com.example.martijn.b0unce;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.martijn.b0unce.model.Map;
import com.example.martijn.b0unce.model.MapObject;
import com.example.martijn.b0unce.model.obstacles.Obstacle;
import com.example.martijn.b0unce.model.resources.GamePoint;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Game extends AppCompatActivity {

    Map map;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private ImageView ball;
    private TextView bounce;

    private GamePoint down;
    private GamePoint up;

    private float columnWidth;
    private float columnHeight;

    private TableRow column;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        map = (Map) getIntent().getSerializableExtra("level");
        map.getBall().setMap(map);

        TableLayout content = (TableLayout) findViewById(R.id.game);

        column = null;

        for (int row = 0; row < 10; row++) {
            TableRow tablerow = new TableRow(this);

            tablerow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));

            for (int col = 0; col < 10; col++) {
                TableRow tablecolumn = new TableRow(this);

                tablecolumn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));
                tablecolumn.setPadding(2, 2, 2, 2);

                TextView tv = new TextView(this);
                tv.setText("");
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT, 1.0f));

                MapObject f = map.getObstacle(col, row);

                if(f == null)
                    tv.setBackgroundColor(0xff1a1a1a);
                else if(f instanceof Obstacle)
                    tv.setBackgroundColor(((Obstacle)f).getTexture());

                tablecolumn.addView(tv);
                column = tablecolumn;
                tablerow.addView(tablecolumn);
            }
            content.addView(tablerow);
        }

        RelativeLayout rel = (RelativeLayout) findViewById(R.id.gameOverlay);

        TextView max = ((TextView)findViewById(R.id.maxBounces));
        max.setText(map.getMaxBounces()+"");

        ball = new ImageView(this);
        ball.setImageResource(R.drawable.circle);
        rel.addView(ball);

        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        down = new GamePoint(event.getX(), event.getY());

                        return true;

                    case MotionEvent.ACTION_UP:

                        up = new GamePoint(event.getX(), event.getY());

                        double rad = Math.atan2(down.y-up.y,down.x-up.x) + Math.PI;
                        double angle = (rad * 180 / Math.PI + 360) % 360;
                        map.Swipe(((int) angle));

                        /*AlertDialog.Builder alertbox = new AlertDialog.Builder(Game.this);
                        alertbox.setMessage((rad * 180 / Math.PI + 180) % 360 + "rad");
                        alertbox.setIcon(android.R.drawable.ic_dialog_alert);
                        alertbox.show();*/

                        return true;

                }

                return false;
            }
        });

        bounce = (TextView) findViewById(R.id.curBounces);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        Thread timer = new Thread() { //new thread
            public void run() {
                boolean run = true;
                try {
                    do {
                        sleep(1000/30);

                        if(map.getBall().getBounces() > map.getMaxBounces()) {
                            run = false;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView failed = (TextView) findViewById(R.id.failedTxt);
                                    failed.setVisibility(View.VISIBLE);
                                }
                            });
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub

                                ball.setX(map.getBall().getPosition().x * column.getMeasuredWidth() );
                                ball.setY(map.getBall().getPosition().y * column.getMeasuredHeight());
                                bounce.setText(map.getBall().getBounces() + "");
                            }
                        });



                    }
                    while (run == true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                }
            };
        };
        timer.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    map.tick();
                    try {
                        Thread.sleep(1000 / 30);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();

    }

    public void reTry(View v) { Intent r = new Intent(Game.this, Game.class); map.reset(); r.putExtra("level", this.map); startActivity(r); }

    public void startHome(View v) {
        startActivity(new Intent(Game.this, Home.class));
    }

    public void back(View v) {
        //NavUtils.navigateUpFromSameTask(this);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Game Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.martijn.b0unce/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Game Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.martijn.b0unce/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
