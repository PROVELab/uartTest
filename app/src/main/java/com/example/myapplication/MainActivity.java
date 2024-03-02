package com.example.myapplication;

import static com.example.myapplication.UARTParse.uart_parse.bits_in_data_section;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import com.example.myapplication.UARTParse.uart_parse;
import com.example.myapplication.UARTParse.Sensors_CFG;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText hexCode;
    TextView TP1;
    TextView TP2;
    TextView TP3;
    TextView TP4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.TP1);
        hexCode = (EditText) findViewById(R.id.HexCodes);
        TP1 = (TextView) findViewById(R.id.TP1D);
        TP2 = (TextView) findViewById(R.id.TP2D);
        TP3 = (TextView) findViewById(R.id.TP3D);
        TP4 = (TextView) findViewById(R.id.TP4D);
        Log.d("Stuff", hexCode + "" + TP1 + "" + TP2 + ""+ TP3 + TP4);

        textView.setText("UART HexCode Testing");
    }

    //method for button clicked
    public void packetParse(View view) {
        String hex = String.valueOf(hexCode.getText());
        if (!hex.isEmpty())
        {
            byte[] header = uart_parse.bytesFromHex(hex.substring(0, Sensors_CFG.HEADER_SIZE_BYTES * 2));
            String[] available_sensors = uart_parse.parse_header(header);
            int bits = bits_in_data_section(available_sensors);
            int bytes = (bits != 0) ? (bits / 8) + 1 : 0;
            byte[] data_section = uart_parse.bytesFromHex(hex.substring(Sensors_CFG.HEADER_SIZE_BYTES * 2, (Sensors_CFG.HEADER_SIZE_BYTES + bytes)* 2));
            Map<String, Integer> recv_data = uart_parse.parse_packet(data_section, available_sensors);
            //byte[] crc = uart_parse.bytesFromHex(hex.substring((Sensors_CFG.HEADER_SIZE_BYTES + bytes)*2));
            textView.setText("Packet " + hexCode.getText() + " Data:");
            //Type shi data displation
            int D1 = recv_data.get("S4");
            int D2 = recv_data.get("S5");
            int D3 = recv_data.get("S");
            int D4 = recv_data.get("TP1");
            Log.d("LMAO", "HERE");
            Log.d("HereLLL", "" + D1);
            TP1.setText("" + D1 );
            TP2.setText("" + D2);
            TP3.setText("" + D3);
            TP4.setText("" + D4);
        }
        else {
            TP1.setText("N/A");
            TP2.setText("N/A");
            TP3.setText("N/A");
            TP4.setText("N/A");
        }

        Log.d("ButtonTest", "Button Clicked");
    }

    //        int i = 5;
//        //DynamicTextSet(5);
//        textView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                boolean ye = true;
//                //int i = 0;
//                textView.setText(String.valueOf(i));
//                Log.d("testest", "whatever you want");
//
////                while (ye){
////                    textView.setText(String.valueOf(i));
////                    i++;
////                }
//            }
//        });
//    void DynamicTextSet(int counter){
//        for (int i = 0; i < counter; i++){
//            textView.setText(i);
//            SystemClock.sleep(1000);
//        }
//
//    }
}