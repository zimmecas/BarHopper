package edu.gvsu.cis.zimmecas.barhopper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Casey on 4/21/2016.
 */
public class getBarsTask extends AsyncTask<String, Void, ArrayList<Bar>> {
    private ProgressDialog pdia;
    Context context;
    ArrayList<Bar> barArr;
    Document doc;
    Bar b;
    String m;

    public getBarsTask(Context context){
        this.context = context;
        pdia = new ProgressDialog(this.context);
    }

    @Override
    protected ArrayList<Bar> doInBackground(String... URLs) {
        for (String u : URLs){
            try {
                org.jsoup.Connection mLive;
                mLive = Jsoup.connect(u);
                doc = mLive.get();
                Element innerDetailsSubLeft = doc.select("div.innerDetailsSubLeft").first();//innerDetailsSubLeft is an element containing multiple "resultWrapper" of bars
                Elements resultWrappers = innerDetailsSubLeft.select("div.resultWrapper");//resultWrappers is an ELEMENTS containing every element of <div class="resultWrapper">...</div> inside of innerDetailsSubLeft
                Elements resultWrappers_verified = innerDetailsSubLeft.select("div.resultWrapper verified");
                for (Element barWrapper_verified : resultWrappers_verified){
                    Elements h3_verified = barWrapper_verified.select("a[href]");
                    Elements address_verified = barWrapper_verified.select("p.address");
                    Elements detailsSub_verified = barWrapper_verified.select("div.detailsSub");
                    String href =  h3_verified.attr("abs:href");
                    String name = h3_verified.select("a").attr("title");
                    String streetAddress = address_verified.select("p.address").text() + address_verified.select("p.address").select("span[class=city}").text() + address_verified.select("p.address").select("spen[class=zip]").text();
                    String phoneNumber = detailsSub_verified.select("span[class=tel]").text();
                    barArr.add(b = new Bar(href, name, streetAddress, phoneNumber));
                }
                for (Element barWrapper : resultWrappers) {
                    Elements h3 = barWrapper.select("a[href]");
                    Elements address = barWrapper.select("p.address");
                    Elements detailsSub = barWrapper.select("div.detailsSub");
                    String href =  h3.attr("abs:href");
                    String name = h3.select("a").attr("title");
                    String streetAddress = address.select("p.address").text() + address.select("p.address").select("span[class=city}").text() + address.select("p.address").select("spen[class=zip]").text();
                    String phoneNumber = detailsSub.select("span[class=tel]").text();
                    barArr.add(b = new Bar(href, name, streetAddress, phoneNumber));
                }
            } catch (IOException ex){
                System.out.println("IOException yo");
                ex.printStackTrace();
            }
        }
        return barArr;
    }



    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        barArr = new ArrayList<>();
        pdia.setMessage("Loading Bars...");
        pdia.show(context, "", "Loading Bars...", true, false);
    }

    @Override
    protected void onPostExecute(ArrayList<Bar> result){
        try {
            pdia.dismiss();
        } catch (Exception e){
            e.printStackTrace();
        }
        MainActivity.setBars(result);
    }
}
