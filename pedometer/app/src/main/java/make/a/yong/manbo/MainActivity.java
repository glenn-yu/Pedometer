package make.a.yong.manbo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    Intent manboService;
    BroadcastReceiver receiver;

    boolean flag = true;
    String serviceData;
    TextView countText;
    Button playingBtn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manboService = new Intent(this, StepCheckService.class);
        receiver = new PlayingReceiver();

        countText = (TextView) findViewById(R.id.stepText);
        playingBtn = (Button) findViewById(R.id.btnStopService);

        playingBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (flag) {
                    // TODO Auto-generated method stub
                    try {

                        IntentFilter mainFilter = new IntentFilter("make.a.yong.manbo");

                        registerReceiver(receiver, mainFilter);
                        startService(manboService);
                    } catch (Exception e) {
                        // TODO: handle exception
                        Toast.makeText(getApplicationContext(), e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {

                    playingBtn.setText("Go !!");

                    // TODO Auto-generated method stub
                    try {

                        unregisterReceiver(receiver);

                        stopService(manboService);

                        // txtMsg.setText("After stoping Service:\n"+service.getClassName());
                    } catch (Exception e) {
                        // TODO: handle exception
                        Toast.makeText(getApplicationContext(), e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }

                flag = !flag;

            }
        });

    }

    class PlayingReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("PlayignReceiver", "IN");
            serviceData = intent.getStringExtra("stepService");
            countText.setText(serviceData);
            Toast.makeText(getApplicationContext(), "Playing game", Toast.LENGTH_SHORT).show();

        }
    }

}
