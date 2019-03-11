package com.eniserkaya.loginpanel;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class CagriAdapter extends ArrayAdapter<CagriKaydi> {

    public CagriAdapter(Context context, int resource, List<CagriKaydi> cagriKaydiList) {
        super(context, resource, cagriKaydiList);
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.list_item,parent,false);
        }

        TextView arayanAdi,arayanNo,aramaZamani,aramaTipi,aramaSuresi;
        arayanAdi = convertView.findViewById(R.id.arayanAdi);
        arayanNo = convertView.findViewById(R.id.arayanNo);
        aramaZamani = convertView.findViewById(R.id.aramaZamani);
        aramaTipi = convertView.findViewById(R.id.aramaTipi);
        aramaSuresi = convertView.findViewById(R.id.aramaSuresi);

        CagriKaydi cagriKaydi = getItem(position);

        if(cagriKaydi.getArayanAdi()!=null)
            arayanAdi.setText(cagriKaydi.getArayanAdi());
        else{
            arayanAdi.setText("Kayıtlı Değil");
        }

        arayanNo.setText(cagriKaydi.getArayanNo());


        return convertView;
    }
}
