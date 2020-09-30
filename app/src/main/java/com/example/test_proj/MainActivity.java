package com.example.test_proj;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    static String TITLE = "Result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int[] price = {100, 200, 50, 33, 89, 112, 526, 923, 123, 547, 98, 1235, 9384, 213, 464, 146};
        int discount = 35;
        int offset = 4;
        int readLength = 4;

        Log.d(TITLE, "Result:");

        int[] result;

        try {
            result = decryptData(price, discount, offset, readLength);

            for(int res:result){
                Log.d(TITLE, String.valueOf(res));
            }
        } catch (IncorrectRangeException e) {
            Log.d(TITLE, "Incorrect range");
        } catch (NullPointerException e){
            Log.d(TITLE, "Empty massive");
        } catch (IncorrectPercentException e){
            Log.d(TITLE, "Incorrect percent");
        } catch (Exception e) {
            Log.d(TITLE, "Smth...");
            e.printStackTrace();
        }
    }

    public @Nullable int[] decryptData(@NonNull int[] price,
                      @IntRange(from = 1, to = 99) int discount,
                      @IntRange(from = 0) int offset,
                      @IntRange(from = 1)int readLength) throws Exception {

        if(price == null)
            throw new NullPointerException();
        if(offset + readLength > price.length - 1 || offset < 0 || readLength < 1)
            throw new IncorrectRangeException();
        if(discount < 1 || discount > 99)
            throw new IncorrectPercentException();


        int[] result = new int[readLength];
        float disc = 1 - (float)discount / 100f;

        for(int i = offset; i < offset + readLength; i++){
            result[i - offset] = (int)(price[i] * disc);
        }

        return result;
    }


    class IncorrectRangeException extends Exception{}

    class IncorrectPercentException extends Exception{}
}