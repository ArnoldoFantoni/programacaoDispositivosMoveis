package com.ifsc.app_aula1;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //String[] nomes =  new String[]{"Helena", "Livia", "Gabi 2026", "Pedro", "Romulo", "Gabriel", "Antonio", "Miguel", "Arnoldo", "Lucas", "Issadora"};
    //ListView lv;

    ListView listView;
    ArrayList<PackageInfo> installeAdpp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        findViewById(R.id.listview_apps);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> packageInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);//lista de tudo

        /* Cria um Intent com a ação ACTION_MAIN, que é usada para filtrar aplicativos
        que tenham uma atividade principal. O segundo argumento null indica que não há
        um componente específico a ser direcionado neste momento.*/
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);

      /////////////////////////
        packageInfoList.clear();
        for(ResolveInfo resolveInfo : appList){
            try{
                String packageName = resolveInfo.activityInfo.packageName;
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                installeAdpp.add(packageInfo);
                packageInfoList.add( packageInfo.applicationInfo);
            }catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }
        }


        AppAdapter appAdapter = new AppAdapter(this,R.layout.item_lista_app, packageInfoList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(appAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            PackageInfo packageInfo = installeAdpp.get(position);
            String packageName = packageInfo.packageName;
            Intent lauchIntent = packageManager.getLaunchIntentForPackage(packageName);
            if(lauchIntent != null){
                startActivity(lauchIntent);
            }
        });

        /*
        lv = findViewById(R.id.listView);
        AdpaterNomes adapter = new AdpaterNomes(this,
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.item_lista,
                R.id.edNome,
                nomes);
        lv.setAdapter(adapter);
        */

        /*
        lv.setOnItemClickListener( (parent, view, position, id) -> {
            Toast.makeText(this, nomes[position], Toast.LENGTH_LONG).show();
        });
        */
    }
}