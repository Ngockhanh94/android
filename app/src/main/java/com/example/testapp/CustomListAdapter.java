package com.example.testapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.testapp.Model.GetListViolationHistoriesModel.Datum;
import com.example.testapp.Model.GetListViolationHistoriesModel.ListViolationHistoriesResponseModel;
import com.example.testapp.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Callback;

public class CustomListAdapter  extends BaseAdapter {

    private List<Datum> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    private static String LINK_VIOLATION_HISTORY_IMAGE = "http://104.154.118.39/Images/ViolationHistoryImage/";
    private static String LINK_REPORT_IMAGE = "http://104.154.118.39/Images/ReportImage/";
    private static String LINK_NEWS_IMAGE = "http://104.154.118.39/Images/NewsImage/";

    public CustomListAdapter(Context aContext, List<Datum> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fj_violation_histories, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.images);
            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.registrationDateView = (TextView) convertView.findViewById(R.id.registrationDate);
            holder.plateView = (TextView) convertView.findViewById(R.id.plate);
            holder.isDealtView = (TextView) convertView.findViewById(R.id.isDealt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Datum datum = this.listData.get(position);
        Picasso.with(context).load(LINK_VIOLATION_HISTORY_IMAGE + datum.getImages()).into(holder.imageView);
        holder.titleView.setText(datum.getTitle());
        holder.registrationDateView.setText(ConvertISODateToDatimeVI(datum.getRegistrationDate()));
        holder.plateView.setText(datum.getPlate());
        if(datum.getIsDealt()){
            holder.isDealtView.setText("Đã xử lý");
            holder.isDealtView.setTextColor(ContextCompat.getColor(context, R.color.cl_da_xu_ly));
        }else{
            holder.isDealtView.setText("Chưa xử lý");
            holder.isDealtView.setTextColor(ContextCompat.getColor(context, R.color.cl_chua_xu_ly));
        }

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }


    private String ConvertISODateToDatimeVI(String isoDate){
        OffsetDateTime odt = OffsetDateTime.parse( isoDate );
        Instant instant = odt.toInstant();
        instant = instant.minus(Duration.ofDays(1));
        Date date = Date.from( instant );
        String datetimeVi = "";
        int MM = odt.getMonthValue();
        int dd = odt.getDayOfMonth();
        int yyyy = odt.getYear();
        int hh = date.getHours();
        int mm = date.getMinutes();
        int ss = date.getSeconds();
        if(hh >= 10) datetimeVi += String.valueOf(hh); else datetimeVi += ("0" + String.valueOf(hh));
        if(mm >= 10) datetimeVi += ":" + String.valueOf(mm); else datetimeVi += ":" + ("0" + String.valueOf(mm));
        if(ss >= 10) datetimeVi += ":" + String.valueOf(ss); else datetimeVi += ":" + ("0" + String.valueOf(ss));
        if(dd >= 10) datetimeVi += " " + String.valueOf(dd); else datetimeVi += " " + ("0" + String.valueOf(dd));
        if(MM >= 10) datetimeVi += "/" + String.valueOf(MM); else datetimeVi += "/" + ("0" + String.valueOf(MM));
        return datetimeVi + "/" + String.valueOf(yyyy);
    }

    static class ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView plateView;
        TextView registrationDateView;
        TextView isDealtView;
    }

}