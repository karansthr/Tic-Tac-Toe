package jetray.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Typeface;
import android.content.res.Configuration;
import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;


public class afterstart extends AppCompatActivity {

    boolean easy;
    boolean medium;
    boolean hard;
    boolean impossible;
    Random r = new Random();

    int flag = 0 ,ax=10 , zero=1 ,sensorflag=0, win=0 , i , game = 1 ,prevrow,prevcol;
    int summ=0 ,ctrflag =0 ,night =0,resetchecker = 1,currentgamedonechecker = 0  ;
    int score1 = 0 , score2 =0 ,drawchecker =0;
    static int [][] tracker = new int [3][3];
    int [] sum = new int [8];
    static  int [][] buttonpressed = new int[3][3];

    boolean player1ax ;
    boolean selectedsingleplayer ;

    TextView p1;
    TextView p2;
    CharSequence player1 ="player 1" ;
    CharSequence player2 ="player 2"  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_afterstart);

         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CharSequence [] players = getIntent().getCharSequenceArrayExtra("playersnames");
        player1ax = getIntent().getBooleanExtra("player1ax",true);
        selectedsingleplayer = getIntent().getBooleanExtra("selectedsingleplayer",true);

        easy = getIntent().getBooleanExtra("easy",false);
        medium = getIntent().getBooleanExtra("medium",false);
        hard = getIntent().getBooleanExtra("hard",false);
        impossible = getIntent().getBooleanExtra("impossible",false);



        if(player1ax)
        {
            ax =1;
            zero = 10;
        }



        player1 = players[0];
        player2 = players[1];
        p1 = (TextView) findViewById(R.id.playerone);
        p2 = (TextView) findViewById(R.id.playertwo);

        p1.setText(player1);
        p2.setText(player2);

        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();

    }



    public void kzz (View view) {



        if(win == 0 && buttonpressed[0][0] ==0) {
            if (flag % 2 == 0)
                tracker[0][0] = ax ;
            else
                tracker[0][0] = zero ;

           printBoard();
            winchecker();
            cpuplay();
            flag++;
            buttonpressed[0][0]++;
        }
    }



    public void kzo(View view) {

        if(win == 0 && buttonpressed[0][1] ==0) {
            if(flag % 2 == 0)     tracker[0][1] = ax;
            else                  tracker[0][1] = zero;

            printBoard();
            winchecker();
            cpuplay();
            buttonpressed[0][1]++;
            flag++;
        }
    }
    public void kzt(View view) {
        if(win == 0 && buttonpressed[0][2] ==0) {
            if (flag % 2 == 0) tracker[0][2] = ax;
            else               tracker[0][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            buttonpressed[0][2]++;
            flag++;
        }
    }
    public void koz(View v) {
        if(win == 0 && buttonpressed[1][0] ==0) {
            if (flag % 2 == 0)   tracker[1][0] = ax;
            else                 tracker[1][0] = zero;

            printBoard();
            winchecker();
            cpuplay();

            ++buttonpressed[1][0];
            flag++;
        }
    }
    public void koo(View v) {
        if(win == 0 && buttonpressed[1][1] ==0) {
            if (flag % 2 == 0)   tracker[1][1] = ax;
            else                 tracker[1][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][1];
            flag++;
        }
    }

    public void kot(View v) {
        if(win == 0 && buttonpressed[1][2] ==0) {
            if (flag % 2 == 0)    tracker[1][2] = ax;
            else                  tracker[1][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][2];
            flag++;
        }
    }
    public void ktz(View v) {
        if(win == 0 && buttonpressed[2][0] ==0) {
            if (flag % 2 == 0)  tracker[2][0] = ax;
            else               tracker[2][0] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][0];
            flag++;
        }
    }
    public void kto(View v) {
        if(win == 0 && buttonpressed[2][1] ==0){
            if (flag % 2 == 0)    tracker[2][1] = ax;
            else                  tracker[2][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][1];
            flag++;
        }
    }
    public void ktt(View v) {
        if(win == 0 && buttonpressed[2][2] ==0) {
            if (flag % 2 == 0)    tracker[2][2] = ax;
            else                  tracker[2][2] = zero;

             printBoard();
            winchecker();
            cpuplay();
            ++ buttonpressed[2][2];
            flag++;
        }
    }

    public void cpuplay() {
        if ((selectedsingleplayer) && (win == 0)) {


                if       (ifcpuwin()) ;
                else if  (ifopowin()) ;
                else if  (emptycentre()) ;
                else if  (emptycorner()) ;
                else      emptyany();



          /***  final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {

                           //add code to be executed after a pause

                        }
                    });
                }
            }, 80);****/
            printBoard();
            winchecker();

            flag++;
            return;
        }
    }

    public boolean ifcpuwin(){
        if(!easy) {
            for (i = 0; i < 8; i++) {
                if (sum[i] == 2 * zero) {
                    if (i == 0) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[0][x] == 0)
                                tracker[0][x] = zero;
                    }

                    if (i == 1) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[1][x] == 0)
                                tracker[1][x] = zero;
                    }
                    if (i == 2) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[2][x] == 0)
                                tracker[2][x] = zero;
                    }

                    if (i == 3) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[x][0] == 0)
                                tracker[x][0] = zero;
                    }

                    if (i == 4) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][1] == 0)
                                tracker[x][1] = zero;
                    }

                    if (i == 5) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][2] == 0)
                                tracker[x][2] = zero;
                    }
                    if (i == 6) {

                        for (int y = 0; y < 3; y++)
                            for (int x = 0; x < 3; x++)
                                if (x == y)
                                    if (tracker[x][y] == 0)
                                        tracker[x][y] = zero;
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0)
                            tracker[0][2] = zero;
                        else if (tracker[1][1] == 0)
                            tracker[1][1] = zero;
                        else tracker[2][0] = zero;

                    }
                    return true;
                }
            }
        }
        return false;
    }




    public boolean ifopowin() {
        if ((!easy) || (!medium)) {

            for (i = 0; i < 8; i++) {
                if (sum[i] == 2 * ax) {
                    if (i == 0) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[0][x] == 0) {
                                tracker[0][x] = zero;
                                buttonpressed[0][x]++;
                            }
                    }

                    if (i == 1) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[1][x] == 0) {
                                tracker[1][x] = zero;
                                buttonpressed[1][x]++;
                            }
                    }
                    if (i == 2) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[2][x] == 0) {
                                tracker[2][x] = zero;
                                buttonpressed[2][x]++;
                            }
                    }

                    if (i == 3) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[x][0] == 0) {
                                tracker[x][0] = zero;
                                buttonpressed[x][0]++;
                            }
                    }

                    if (i == 4) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][1] == 0) {
                                tracker[x][1] = zero;
                                buttonpressed[x][1]++;
                            }
                    }

                    if (i == 5) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][2] == 0) {
                                tracker[x][2] = zero;
                                buttonpressed[x][2]++;
                            }
                    }
                    if (i == 6) {

                        for (int y = 0; y < 3; y++)
                            for (int x = 0; x < 3; x++)
                                if (x == y)
                                    if (tracker[x][y] == 0) {
                                        tracker[x][y] = zero;
                                        buttonpressed[x][y]++;
                                    }


                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0) {
                            tracker[0][2] = zero;
                            buttonpressed[0][2]++;
                        } else if (tracker[1][1] == 0) {
                            tracker[1][1] = zero;
                            buttonpressed[1][1]++;
                        } else {
                            tracker[2][0] = zero;
                            buttonpressed[2][0]++;
                        }


                    }
                    return true;
                }
            }

        }
        return false;
    }

    public boolean emptycentre(){
        if(impossible || hard) {
            if (tracker[1][1] == 0) {
                tracker[1][1] = zero;
                buttonpressed[1][1]++;
                return true;
            }
        }
        return false;
    }

    public boolean emptycorner(){


        if(hard || impossible)
            if(((tracker[0][0] + tracker[2][2])  == 2 * ax )||( ( tracker[0][2] + tracker[2][0])== 2 *  ax))
            {
                for(int k =0 ;k<3;k++)
                    for(int j=0;j<3;j++)
                        if((k+j)%2==1){
                            if(tracker[k][j]==0)
                                tracker[k][j] = zero ;
                            buttonpressed[k][j]++;
                            return true;
                        }
            }


        if(impossible)
            if (sum[6] == zero || sum[7] == zero) {
                if (sum[6] == zero) {
                    if ((sum[0] + sum[3]) > (sum[2] + sum[5]))
                    { tracker[0][0] = zero;
                        buttonpressed[0][0]++;
                    }
                    else
                    {  tracker[2][2] = zero;
                        buttonpressed[2][2]++;
                    }
                    return true;
                }

                if (sum[7] == zero) {
                    if ((sum[0] + sum[5]) > (sum[3] + sum[2]))
                    { tracker[0][2] = zero;
                        buttonpressed[0][2]++;
                    }
                    else
                    { tracker[2][0] = zero;
                        buttonpressed[2][0]++;
                    }
                    return true;
                }

            }




        for(int i=0;i<3;i++)
        {
            if(tracker[0][i]==ax){
                if(tracker[0][0]==0)
                {
                    tracker[0][0] = zero;
                    buttonpressed[0][0]++;
                    return true;
                }
                if(tracker[0][2]==0)
                {
                    tracker[0][2]=zero;
                    buttonpressed[0][2]++;
                    return true ;
                }
            }
        }

        for(int i=0;i<3;i++)
        {

            if(tracker[2][i]==ax){
                if(tracker[2][0]==0)
                {
                    tracker[2][0] = zero;
                    buttonpressed[2][0]++;
                    return true;
                }
                if(tracker[2][2]==0)
                {
                    tracker[2][2]=zero;
                    buttonpressed[2][2]++;
                    return true;
                }
            }
        }
        for(int i=0;i<3;i++)
        {
            if(tracker[i][0]==ax){
                if(tracker[0][0]==0)
                {
                    tracker[0][0] = zero;
                    buttonpressed[0][0]++;
                    return true;
                }
                if(tracker[2][0]==0)
                {
                    tracker[2][0]=zero;
                    buttonpressed[2][0]++;
                    return true;
                }
            }
        }
        for(int i=0;i<3;i++)
        {
            if(tracker[i][2]==ax){
                if(tracker[0][2]==0)
                {
                    tracker[0][2] = zero;
                    buttonpressed[0][2]++;
                    return true;
                }
                if(tracker[2][2]==0)
                {
                    tracker[2][2]=zero;
                    buttonpressed[2][2]++;
                    return true;
                }
            }
        }
        return false;

    }

    public void emptyany(){

        if(ctrflag==0)
            while(true){
                int x = rand();
                int y = rand();

                if(tracker[x][y]==0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;

                }
            }

        for(int x=0;x<3;x++)
            for(int y=0;y<3;y++)
                if(tracker[x][y]==0)
                {
                    tracker[x][y] = zero ;
                    buttonpressed[x][y]++;
                    return;
                }


    }
    public int rand(){
        return  r.nextInt(3);
    }

    public void printBoard(){
 ImageView q1,q2,q3,q4,q5,q6,q7,q8,q9 ;

 q1= (ImageView)findViewById(R.id.tzz);
 q2= (ImageView)findViewById(R.id.tzo);
 q3= (ImageView)findViewById(R.id.tzt);
 q4= (ImageView)findViewById(R.id.toz);
 q5= (ImageView)findViewById(R.id.too);
 q6= (ImageView)findViewById(R.id.tot);
 q7= (ImageView)findViewById(R.id.ttz);
 q8= (ImageView)findViewById(R.id.tto);
 q9= (ImageView)findViewById(R.id.ttt);





        if(tracker[0][0]==1)     q1.setImageResource(R.drawable.x);
        if(tracker[0][0]==10)    q1.setImageResource(R.drawable.oo);


        if(tracker[0][1]==1)     q2.setImageResource(R.drawable.x);
        if(tracker[0][1]==10)    q2.setImageResource(R.drawable.oo);


        if(tracker[0][2]==1)     q3.setImageResource(R.drawable.x);
        if(tracker[0][2]==10)    q3.setImageResource(R.drawable.oo);


        if(tracker[1][0]==1)     q4.setImageResource(R.drawable.x);
        if(tracker[1][0]==10)    q4.setImageResource(R.drawable.oo);

        if(tracker[1][1]==1)     q5.setImageResource(R.drawable.x);
        if(tracker[1][1]==10)    q5.setImageResource(R.drawable.oo);


        if(tracker[1][2]==1)     q6.setImageResource(R.drawable.x);
        if(tracker[1][2]==10)    q6.setImageResource(R.drawable.oo);

        if(tracker[2][0]==1)     q7.setImageResource(R.drawable.x);
        if(tracker[2][0]==10)    q7.setImageResource(R.drawable.oo);


        if(tracker[2][1]==1)    q8.setImageResource(R.drawable.x);
        if(tracker[2][1]==10)   q8.setImageResource(R.drawable.oo);

        if(tracker[2][2]==1)    q9.setImageResource(R.drawable.x);
        if(tracker[2][2]==10)   q9.setImageResource(R.drawable.oo);

        resetchecker++;
    }


    public void winchecker(){
        ctrflag++;
        sum[0]= tracker[0][0] + tracker[0][1] + tracker[0][2] ;
        sum[1]= tracker[1][0] + tracker[1][1] + tracker[1][2] ;
        sum[2]= tracker[2][0] + tracker[2][1] + tracker[2][2] ;
        sum[3]= tracker[0][0] + tracker[1][0] + tracker[2][0] ;
        sum[4]= tracker[0][1] + tracker[1][1] + tracker[2][1] ;
        sum[5]= tracker[0][2] + tracker[1][2] + tracker[2][2] ;
        sum[6]= tracker[0][0] + tracker[1][1] + tracker[2][2] ;
        sum[7]= tracker[0][2] + tracker[1][1] + tracker[2][0] ;


        currentgamedonechecker++;
        resetchecker++;

        for( int i=0;i<8;i++)
            if(sum[i] == 3 || sum[i] == 30)
            {
                win++;
                if((sum[i]==3)&& ( ax  == 1)) {
                    Toast.makeText(this,"" + player1 + " wins!",Toast.LENGTH_SHORT).show();
                    score1++;
                    TextView q1 = (TextView) findViewById(R.id.p1score);
                    q1.setText(""+score1);

                }
                if((sum[i]==3)&& ( zero  == 1)) {
                    Toast.makeText(this,"" + player2 + " wins!",Toast.LENGTH_SHORT).show();
                    score2++;
                    TextView q1 = (TextView) findViewById(R.id.p2score);
                    q1.setText(""+score2);

                }
                if((sum[i]==30)&& ( ax  == 10)) {
                    Toast.makeText(this,"" + player1 + " wins!",Toast.LENGTH_SHORT).show();
                    score1++;
                    TextView q1 = (TextView) findViewById(R.id.p1score);
                    q1.setText(""+score1);

                }
                if((sum[i]==30)&& ( zero  == 10)) {
                    Toast.makeText(this,"" + player2 + " wins!",Toast.LENGTH_SHORT).show();
                    score2++;
                    TextView q1 = (TextView) findViewById(R.id.p2score);
                    q1.setText(""+score2);

                }

            }

        if((ctrflag == 9)&&(win==0)) {
            Toast.makeText(this, "This is a draw !", Toast.LENGTH_SHORT).show();
            drawchecker++;
        }



    }  //end winchecker()

   public void playmore(View view) {
        if ((drawchecker > 0) || (win > 0)) {
            game++;
            TextView qq = (TextView) findViewById(R.id.gamenumber);
            qq.setText("" + game);

            for(int i=0;i<8;i++)
                sum[i]=0;

            drawchecker = 0;


            ImageView q1,q2,q3,q4,q5,q6,q7,q8,q9 ;
            q1= (ImageView)findViewById(R.id.tzz);
            q2= (ImageView)findViewById(R.id.tzo);
            q3= (ImageView)findViewById(R.id.tzt);
            q4= (ImageView)findViewById(R.id.toz);
            q5= (ImageView)findViewById(R.id.too);
            q6= (ImageView)findViewById(R.id.tot);
            q7= (ImageView)findViewById(R.id.ttz);
            q8= (ImageView)findViewById(R.id.tto);
            q9= (ImageView)findViewById(R.id.ttt);
            q1.setImageDrawable(null);
            q2.setImageDrawable(null);
            q3.setImageDrawable(null);
            q4.setImageDrawable(null);
            q5.setImageDrawable(null);
            q6.setImageDrawable(null);
            q7.setImageDrawable(null);
            q8.setImageDrawable(null);
            q9.setImageDrawable(null);

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    buttonpressed[i][j] = 0;

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tracker[i][j] = 0;


            if ((game + 1) % 2 == 0)
                Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "" + player2 + "\'s turn", Toast.LENGTH_SHORT).show();
            win = 0;
            summ = 0;
            ctrflag = 0;
            flag = (game + 1) % 2;
            currentgamedonechecker = 0;

            if (selectedsingleplayer && ( game % 2 == 0 ))
                cpuplay();
        }
    }

  public void resetbutton(View view){
        doreset();
    }

    public void doreset(){

        TextView qq = (TextView) findViewById(R.id.gamenumber);
        qq.setText("" + 1);



        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tracker[i][j] = 0;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttonpressed[i][j]=0;

        ImageView q1,q2,q3,q4,q5,q6,q7,q8,q9 ;

        q1= (ImageView)findViewById(R.id.tzz);
        q2= (ImageView)findViewById(R.id.tzo);
        q3= (ImageView)findViewById(R.id.tzt);
        q4= (ImageView)findViewById(R.id.toz);
        q5= (ImageView)findViewById(R.id.too);
        q6= (ImageView)findViewById(R.id.tot);
        q7= (ImageView)findViewById(R.id.ttz);
        q8= (ImageView)findViewById(R.id.tto);
        q9= (ImageView)findViewById(R.id.ttt);
        q1.setImageDrawable(null);
        q2.setImageDrawable(null);
        q3.setImageDrawable(null);
        q4.setImageDrawable(null);
        q5.setImageDrawable(null);
        q6.setImageDrawable(null);
        q7.setImageDrawable(null);
        q8.setImageDrawable(null);
        q9.setImageDrawable(null);



        win = 0;
        drawchecker =0;
        summ = 0;
        resetchecker=0;
        ctrflag = 0;
        score1 = 0;
        score2 = 0;
        game = 1;
        flag=0;
        currentgamedonechecker = 0;
        TextView qqq = (TextView) findViewById(R.id.p1score);
        qqq.setText("" + score1);
        TextView qqqq = (TextView) findViewById(R.id.p2score);
        qqqq.setText("" + score2);

        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    boolean doubleBackToExitPressedOnce =false;
    @Override
    public void onBackPressed(){
        if(doubleBackToExitPressedOnce){
            super.onBackPressed();
           // doreset();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"touch again to restart !",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                doubleBackToExitPressedOnce = false;
            }
        },2000);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            //doreset();
            startActivity(intent);
        }
        if (id == R.id.about) {
            Intent i = new Intent(this,about.class);
            startActivity(i);
            return true;
        }

        if(id == R.id.daynightmode) {

            if (night % 2 == 0 ) {
                View view = this.getWindow().getDecorView();
                view.setBackgroundColor(Color.parseColor("#000000"));
                item.setTitle("Day Mode");
            } else {
                View view = this.getWindow().getDecorView();
                view.setBackgroundColor(Color.parseColor("#FFFFFF"));
                item.setTitle("Night Mode");
            }
            night++;
        }



        return super.onOptionsItemSelected(item);
    }
}


