package edu.gvsu.cis.zimmecas.barhopper.dummy;

import android.support.v7.widget.RecyclerView;


import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


import edu.gvsu.cis.zimmecas.barhopper.Bar;


/**
 * Created by louissullivan on 4/26/16.
 */

public class MakeCalls implements Callback<List<WordInfo>>{
//List<WordInfo>> {



    @Override
    public void success(List<WordInfo> wordInfos, Response response) {



        for (WordInfo w : wordInfos) {

            System.out.println(w.syllable);
            System.out.println(w.word);
            System.out.println(w.flags);
            System.out.println(w.freq);
            System.out.println(w.score);
        }

    }

    @Override
    public void failure (RetrofitError retrofitError){

    }


}
