package com.example.ameer.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Thread.sleep;

public class BoardView extends SurfaceView implements SurfaceHolder.Callback {
    int score = 0;
    Bitmap mybitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image);//put an image called 'image(you can of change it to other name)' under the folder drawble
    Bitmap candy1 = BitmapFactory.decodeResource(getResources(), R.drawable.candy1);
    Bitmap candy2 = BitmapFactory.decodeResource(getResources(), R.drawable.candy2);
    Bitmap candy3 = BitmapFactory.decodeResource(getResources(), R.drawable.candy3);
    Bitmap candy4 = BitmapFactory.decodeResource(getResources(), R.drawable.candy4);
    Bitmap candy5 = BitmapFactory.decodeResource(getResources(), R.drawable.image);
    Bitmap i1[][] = new Bitmap[10][10];
    float x;
    float y;
    boolean a = true;
    int [][] storage = {{-1,-1},{-1,-1}};

    boolean b = true;

    public BoardView(Context context) {

        super(context);
    // N o t i f y t h e S u r f a c e H o l d e r t h a t you ’ d l i k e t o r e c e i v e S u r f a c e H o l d e r c a l l b a c k s .
                getHolder().addCallback(this);
                setFocusable(true); // Very i m p o r t a n t
                // I n i t i a l i z e game s t a t e v a r i a b l e s and t h e game b o a r d v a r i a b l e s .
                // DON’T RENDER THE GAME YET.
                setWillNotDraw(false);

    }

    @SuppressLint("WrongCall")
    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        /*Canvas c = holder.lockCanvas();
        this.onDraw(c);
        holder.unlockCanvasAndPost(c);*/
        invalidate();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //draw a rectangle at the pos you click on the screen
        int h = getHeight();

        int w = getWidth();
        x = e.getX();
        y = e.getY();
        Canvas c = getHolder().lockCanvas();
        int g = -1, f = -1;
        for (int i = 0; i < 10; i++){
            if ((200 + 100*i < x) && (x < 300 + 100*i)){ //if b is true our x1 and y1 are -1
                g = i;
/*                if (b)
                    storage[0][0] = i;
                else {
                    if (storage[0][0] == i) {
                        storage[0][1] = -1;
                    } else
                        storage[0][1] = i;
                }*/
            }
            if ((500 + 100*i < y) && (y < 600 + 100*i)){
                f = i;
/*                if (b){
                    storage[1][0] = i;
                }
                else {
                    if (storage[1][0] == i) {
                        storage[1][1] = -1;
                    }
                    else
                        storage[1][1] = i;
                }*/
            }



        }
        if (b){
            storage[0][0] = g;
            storage[1][0] = f;
        }
        else{
            if (g == storage[0][0] && f == storage[1][0]){
                System.out.println("same space");
            }
            else if ((g == storage[0][0] && (i1[g][f] == i1[g][storage[1][0] + 1] || i1[g][f] == i1[g][storage[1][0] - 1])) || (f == storage[1][0] && (i1[g][f] == i1[storage[0][0] + 1][f] || i1[g][f] == i1[storage[0][0] - 1][f]))){

                Bitmap[][] i2 = i1.clone();
                Bitmap temp = i2[storage[0][0]][storage[1][0]];//makes copy of i1 and checks if it would result in colors together
                i2[storage[0][0]][storage[1][0]] = i2[g][f];
                i2[g][f] = temp;

                if(setNull(i2) != 0) {

                    storage[0][1] = g; // we need to work on this
                    storage[1][1] = f;
                }
            }
            else {
                System.out.println("space too far");
                storage[0][0] = -1;
                storage[1][0] = -1;
                storage[0][1] = -1;
                storage[1][1] = -1;
            }
        }
        if (storage[0][0] != -1 && storage[1][0] != -1 && storage[0][1] != -1 && storage[1][1] != -1) {
            swap(c, storage[0][0], storage[1][0], storage[0][1], storage[1][1]);
            System.out.println("swapped!" + storage[0][1]);
            storage[0][0] = -1;
            storage[1][0] = -1;
            storage[0][1] = -1;
            storage[1][1] = -1;


        }
        if (storage[0][0] != -1 && storage[1][0] != -1 )
            b = false;
        else
            b = true;

/*        if (x1 != 0 && y1 != 0)
            userTouched(x1,y1);
        else
            userTouched(0,0);*/

  //      Paint paint = new Paint();
  //      paint.setColor(Color.BLUE);
  //      c.drawRect(x, y, x + 100, y + 100, paint);
        getHolder().unlockCanvasAndPost(c);
        System.out.println(storage[0][0]);
        System.out.println(storage[1][0]);
        System.out.println(storage[0][1]);
        System.out.println(storage[1][1]);
        return true;

    }
    public void swap(Canvas c, int x1, int y1, int x2, int y2){
        Bitmap temp;
        Rect can = new Rect();
        temp = i1[x1][y1];
        i1[x1][y1] = i1[x2][y2];
        i1[x2][y2] = temp;
/*        can.set(200 + x1*100, 500 + y1*100, 300 + x1*100, 600 + y1*100);
        c.drawBitmap(i1[x2][y2],null,can,null);
        can.set(200 + x2*100, 500 + y2*100, 300 + x2*100, 600 + y2*100);
        c.drawBitmap(temp, null, can, null);*/
        setScore(setNull(i1));
        fixBoard();
        invalidate();

    }
   /* public void userTouched(int x, int y){
        //reset array if we are sent 0,0 or if array gets 2 values inside of it.
    if (x == 0 && y == 0) {
        storage[0][0] = 0;
        storage[0][1] = 0;
        storage[1][0] = 0;
        storage[1][1] = 0;
    }

    else
        {
            if (storage[0][0] == 0) {
                storage[0][0] = x;
                storage[0][1] = y;
            } else {
                isLegal(i1, storage[0][0], storage[0][1], x, y);
                storage[0][0] = 0;
                storage[0][1] = 0;
                storage[1][0] = 0;
                storage[1][1] = 0;

            }
        }


    }*/

    protected void onDraw(Canvas c) {


        super.onDraw(c);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        int pick;
        Random rand = new Random();
        c.drawColor(Color.BLACK);//draw the background color to red
//        Rect dst = new Rect();
//        dst.set(500, 1500, 1000, 2000);
//        c.drawBitmap(mybitmap, null, dst, null);//draw the image you putted in the folder drawble
        int x, y;
        x = 10;
        y = 10;
     //   Bitmap i1[][] = new Bitmap[10][10];

        int height = getHeight();

        int width = getWidth();


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (a == true) {//on initial draw
                    pick = rand.nextInt(4) + 1;
                    if (pick == 1)
                        i1[i][j] = candy1;
                    if (pick == 2)
                        i1[i][j] = candy2;
                    if (pick == 3)
                        i1[i][j] = candy3;
                    if (pick == 4)
                        i1[i][j] = candy4;
                }
            }
        }
//        if (a)
//            organize();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Rect can = new Rect();
                can.set(200 + 100 * i, 500 + 100 * j, 300 + 100 * i, 600 + 100 * j);
//                can.set(width / 8 + (3 * i * width) / 40, height / 8 + (3 * j * height) / 40, width / 8 + (3 * width) / 40 + (3 * i * width) / 40, height / 8 + (3 * height) / 40 + (3 * j * height) / 40);
                c.drawBitmap(i1[i][j], null, can, null);

            }
        }
        c.drawText(Integer.toString(score), 50, 100, paint);
        if(a)
            organize();
        a = false;
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
    }

    public void refill(Bitmap[][] b, int x, int y){ //refills an x column
        Random rand = new Random();
        Bitmap [][] cx = i1.clone();
        int count = 0;
        int pick;
        for (int i = y; i >= 0; i--){
            if (b[x][i] == candy5)    //finds how many candies were erased
                count++;
        }
        for (int i = y; i >= 0+count; i--){

            b[x][i] = cx[x][i-count]; //moves candies down based on how many were erased
        }
        for (int i = 0; i < count; i++){
            pick = rand.nextInt(4) + 1;
            if (pick == 1)
                b[x][i] = candy1;
            if (pick == 2)
                b[x][i] = candy2; //makes new candies
            if (pick == 3)
                b[x][i] = candy3;
            if (pick == 4)
                b[x][i] = candy4;
            else
                System.out.println("error");
        }
        organize();
    }
    public void organize(){
        int r = 0;
        int count = 0;
        Random rand = new Random();
        int pick;
        Bitmap t;
        pick = rand.nextInt(4) + 1;
        if (pick == 1)
            t = candy1;
        if (pick == 2)
            t = candy2; //makes new candies
        if (pick == 3)
            t = candy3;
        if (pick == 4)
            t = candy4;
        else
            t = candy1;
        //iterate rows and make sure no more than 2 of same candies in a row
        for (int j = 0; j < 10; j++)
             for (int i = 0; i < 8; i++){
                if (i1[i][j] == i1[i+1][j] && i1[i+1][j] == i1[i+2][j]) {
                    r++;
                    pick = rand.nextInt(4) + 1;
                    while (i1[i+1][j] == t) {
                        if (pick == 1)
                            t = candy1;
                        if (pick == 2)
                            t = candy2; //makes new candies
                        if (pick == 3)
                            t = candy3;
                        if (pick == 4)
                            t = candy4;
                    }
                    i1[i+1][j] = t;

                }


             }
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 8; j++){
                if (i1[i][j] == i1[i][j+1] && i1[i][j+1] == i1[i][j+2]) {
                    r++;
                    pick = rand.nextInt(4) + 1;
                    while (i1[i][j+1] == t) {
                        if (pick == 1)
                            t = candy1;
                        if (pick == 2)
                            t = candy2; //makes new candies
                        if (pick == 3)
                            t = candy3;
                        if (pick == 4)
                            t = candy4;
                    }
                    i1[i][j+1] = t;

                }


            }
            if (r != 0)
                organize();

        //iterate columns and make sure no more than 2 of same candies in a column
    }
    String isLegal(Bitmap[][]b, int x1, int y1, int x2, int y2){
        String r = "";
        boolean check = false;
        Bitmap temp;
        if (Math.abs(x1 - x2) == 1 || Math.abs(y1 - y2) == 1){
            temp = b[x1][y1];
            b[x1][y1] = b[x2][y2]; //swap values
            b[x2][y2] = temp;
        }
        for (int j = 0; j < b[0].length; j++){
            while (check == false) {
                for (int i = 2; i < b.length - 2; i++) {
                    if ((b[i][j] == b[i + 1][j] && b[i][j] == b[i - 1][j]) || (b[i][j] == b[i - 1][j] && b[i][j]//checking every single row
                            == b[i - 2][j]) || (b[i][j] == b[i + 1][j] && b[i][j] == b[i + 2][j])) {
                        r += "row" + Integer.toString(j) + Integer.toString(x1) + Integer.toString(x2);
                        check = true;
                    }
                }
            }
        }
        check = false;
        for (int i = 0; i < b.length; i++) {
            while (check = false) {
                for (int j = 2; j < b[0].length - 2; j++) {
                    if ((b[i][j] == b[i][j + 1] && b[i][j] == b[i][j - 1]) || (b[i][j] == b[i][j - 1] && b[i][j]//checking every single row
                            == b[i][j - 2]) || (b[i][j] == b[i][j + 1] && b[i][j] == b[i][j + 2])) {
                        r += "col" + Integer.toString(i) + Integer.toString(y1) + Integer.toString(y2);
                        check = true;
                    }
                }
            }
        }
        System.out.println(r);
        return r; //if string empty, illegal. else itlls be row5col2 or ro5 or col2
    }

    public void findSameCandies(String s, Bitmap [][]b){
        int count = 0;
        if (s.charAt(0) == 'r'){
            int j = Character.getNumericValue(s.charAt(3));
            for (int i = 0; i < b.length; i++){
                int z = i;
                while (b[z][j] == b[z+1][j]){
                    if (z+1 <= b.length) {
                        z++;
                        count++; //we check that z does not exceed the bounds of the array
                        i++;
                    }

                }
                //if(b[i][j] = b[i+1][j])

            }
        }
    }
    public int setNull(Bitmap[][] eee){
        int temp = 0;
        int count = 0;
        int z = 0;
        boolean bb = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                bb = false;
                z = 0;
                temp = 0;
                while(eee[i][j+z] == eee[i][j + 1+z] && eee[i][j + 1+z] == eee[i][j + 2+z]) {
                    temp++;
                    bb = true;
                    if (j + 2 + z < 10)
                        z++;
                    else
                        break;

                }
                if (bb == true)
                {
                    for (int qq = j; qq <= (j+temp+1); qq++)
                          eee[i][qq] = candy5;
                    bb = false;

                }
                count += temp;
            }
        }
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 8; i++) {
                bb = false;
                z = 0;
                temp = 0;
                while(eee[i+z][j] == eee[i+1+z][j] && eee[i+1+z][j] == eee[i+2+z][j]) {
                    temp++;
                    bb = true;
                    if (i + 2 + z < 10)
                        z++;
                    else
                        break;

                }
                if (bb == true)
                {
                    for (int qq = i; qq <= (i+temp+1); qq++)//j+temp+1
                        eee[qq][j] = candy5;
                    bb = false;

                }
                count += temp;
            }
        }
        return count;

    }
    public void setScore(int sc){
        score += sc;
        System.out.println(score);

    }
    public void fixBoard(){//finds null locations and refills them.
        boolean a;
        for (int i = 0; i < 10; i++) {
            a = true;
            for (int j = 9; j >= 0; j--) {
                if (a == true){
                    if(i1[i][j] == candy5){
                        refill(i1, i, j);
                        a = false;
                    }
                }
            }
        }
    }

}

