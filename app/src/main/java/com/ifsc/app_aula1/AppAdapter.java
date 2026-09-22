package com.ifsc.app_aula1;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ApplicationInfo> {

    public AppAdapter(@NonNull Context context, int resource, @NonNull List<ApplicationInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        //Inflando layout do item da lista
        View convertView;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.item_lista_app, parent, false);
        //Recuperando o objeto ApplicationInfo da posição atual
        ApplicationInfo applicationInfo = getItem(position);
        //Recuperando os componentes do layout
        TextView textViewAppName = convertView.findViewById(R.id.app_name);
        ImageView imageViewAppIcon = convertView.findViewById(R.id.app_icon);
        //Setando os valores nos componentes

        imageViewAppIcon.setImageDrawable(applicationInfo.loadIcon(getContext().getPackageManager()));
        textViewAppName.setText(applicationInfo.loadLabel(getContext().getPackageManager()));
        return convertView;
    }
}
