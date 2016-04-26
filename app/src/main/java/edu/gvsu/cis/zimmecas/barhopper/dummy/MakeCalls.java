package edu.gvsu.cis.zimmecas.barhopper.dummy;



import java.util.List;

import edu.gvsu.cis.zimmecas.barhopper.Bar;
import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by louissullivan on 4/26/16.
 */
public class MakeCalls implements CallBack<List<WordInfo>> {

    @Override
    public void success(List<WordInfo> wordInfos, Responce responce) {

        for (WordInfo w : WordInfo) {
            System.out.println(w.name);
            System.out.println(w.address);
            System.out.println(w.name);
            System.out.println(w.name);
        }
    }

    @Override
    public void failure(RetrofitError retrofitError){

    }

}
