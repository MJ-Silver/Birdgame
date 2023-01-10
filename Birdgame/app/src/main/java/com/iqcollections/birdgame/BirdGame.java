package com.iqcollections.birdgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class BirdGame extends View
{
    private Bitmap background;
    private Bitmap bird ;
    private Paint level,score,blueBall,blackBall;
    private int birdx=10,birdy=50,width,height,points=0,birdspeed=10,birdheight;
    private boolean touch_flag=false;
    private int levelscore = 1;
    private int blackrad = 50;
    private int bluex=0 ,bluey=0;
    private int blackx=0 ,blacky=0;



    public BirdGame(Context context) {
        super(context);
        background = BitmapFactory.decodeResource(getResources(),R.drawable.villagescreensize);
        bird = BitmapFactory.decodeResource(getResources(),R.drawable.wingsup);
        //bird[1] = BitmapFactory.decodeResource(getResources(),R.drawable.wingsdown);

        level=new Paint();
        score=new Paint();

        level.setColor(Color.BLACK);
        level.setTextSize(50);
        level.setAntiAlias(true);
        level.setTypeface(Typeface.DEFAULT_BOLD);
        level.setTextAlign(Paint.Align.CENTER);

        score.setColor(Color.BLACK);
        score.setTextSize(50);
        score.setAntiAlias(true);
        score.setTypeface(Typeface.DEFAULT_BOLD);
        score.setTextAlign(Paint.Align.CENTER);

        blueBall = new Paint();
        blueBall.setColor(Color.BLUE);

        blackBall = new Paint();
        blackBall.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         width= canvas.getWidth();
        height = canvas.getHeight();
       // int minBirdY =bird[0].getHeight();
       // int maxBirdY =height-bird[0].getHeight()*3;
        birdheight = bird.getHeight();
        birdy+=birdspeed;
        if(points >= 100 && points < 200){
            levelscore = 2;
            blackrad = 30;
        }else if(points >= 200){
            levelscore = 3;
            blackrad = 10;
        }

        bluex -=15;
        blackx -=15;
        //setting bounds
        if(birdy > height-birdheight){
            birdy = height - birdheight;

        }
        if(birdy<birdheight){
            birdy = birdheight;
        }
        birdspeed+=2;

        if(bluex <=0){
            bluex = width+20;
            bluey =(int)(Math.random()*(height-birdheight-birdheight)+birdheight);
        }
        if(blackx <=0){
            blackx = width+20;
            blacky=(int)(Math.random()*(height-birdheight-birdheight)+birdheight);
        }
        if(collison(bluex,bluey)){
           points+=10;
           bluex=0;
        }
        if(collison(blackx,blacky)){
            points+=10;
            blackx =0;
        }


        canvas.drawBitmap(background,0,0,null);//adding background image
        canvas.drawBitmap(bird,birdx,birdy,null);//adding background image
        //add bird to screen

        canvas.drawText("Level"+levelscore,width/2,50,level);
        canvas.drawText("Score:"+points,width/2+200,50,score);

        canvas.drawCircle(bluex,bluey,15,blueBall);
        canvas.drawCircle(blackx,blacky,blackrad,blackBall);




      //  if(touch_flag){
         //   canvas.drawBitmap(bird[0],birdx,birdy,null);
        //    touch_flag=false;
        //}else
      //  {
           // canvas.drawBitmap(bird[1],birdx,birdy,null);

       // }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

      if(event.getAction()==MotionEvent.ACTION_DOWN){
          birdspeed=-20;

      }

        return true;
    }
    public boolean collison(int xcoord,int ycoord){

        int birdleft,birdRight,birdtop,birdbottom;

        birdleft = birdx;
        birdRight =birdx+bird.getWidth();
        birdtop = birdy;
        birdbottom = birdy-bird.getHeight();

        if(birdleft<xcoord && xcoord<birdRight&&birdbottom<ycoord&&birdtop<ycoord){
            return true;

        }else
        {
            return false;
        }
    }
}
