package com.example.kumararaja.checkipaddress;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView ls;

    EditText ipadd,portt;
    private Button ck;
    private ArrayList<String> itemArray;
    private ArrayAdapter<String> itemAdapter;
    String ip;
    int port, timeout;
    //double ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        portt = (EditText) findViewById(R.id.port_number);
        ipadd = (EditText) findViewById(R.id.ipAdd);
        ck = (Button) findViewById(R.id.add);
        ls = (ListView) findViewById(R.id.listview);

        itemArray = new ArrayList<String>();
        itemArray.clear();

        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemArray);
        ls.setAdapter(itemAdapter);

        ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = "google.com";
                runSystemCommand("ping " + ip);
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

            }

        });

    }

    public static void runSystemCommand(String command) {

        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader inputStream = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            String s = "working";
            // reading output stream of the command
            while ((s = inputStream.readLine()) != null) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {



    }

/*    public static void isReachable(String address) {
        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            boolean isReachable = inetAddress.isReachable(5000);
            System.out.printf("Is the address [%s] reachable? -%s\n", address, isReachable ? "Yes" : "No");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*private String ping(String url) {
        String str="";
        try{
            Process process=Runtime.getRuntime().exec("/system/bin/ping -c 8"+url);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream()));

            int i;
            char[]buffer=new char[4096];
            String output=new StringBuffer();
            while ((i=bufferedReader.read(buffer))>0)
                ((StringBuffer) output).append(buffer,0,1);
            bufferedReader.close();

            str=output.toString();

        } catch (IOException e){
            e.printStackTrace();
        }
        return str;

    }*/

    protected void addItems() {
    }

    public class Mytask extends AsyncTask<String, Integer, Boolean> {


        @Override
        public Boolean doInBackground(String... strings) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ip, port), timeout);
                socket.close();
                //return true;
            } catch (ConnectException ce) {
                ce.printStackTrace();
                //return false;
            } catch (Exception ex) {
                ex.printStackTrace();
                //
            }
         return true;
        }
    }
}

   /* public static boolean isPortOpen(final String ip,final int port,final int timeout){
        try {
            Socket socket=new Socket();
            socket.connect(new InetSocketAddress(ip,port),timeout);
            socket.close();
            return true;
        }
        catch (ConnectException ce){
            ce.printStackTrace();
            return false;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }*/

