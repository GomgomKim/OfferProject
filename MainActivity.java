package com.example.soyoung.tksguqprogram2;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_ENABLE_BT = 10;
    // Sensor accelerometer;
    // boolean angleFlag = false;
    int data = 0;
    //   int[] intTemp = new int[3];
    BluetoothAdapter mBluetoothAdapter;
    char mCharDelimiter = '\n';
    Set<BluetoothDevice> mDevices;
    Button mEdit0;
    Button mEdit135;
    Button mEdit180;
    Button mEdit45;
    Button mEdit90;
    // ToggleButton mEditBack;
    //  float[] mGeomagnetic;
    //  float[] mGravity;
    InputStream mInputStream = null;
    OutputStream mOutputStream = null;
    int mPairedDeviceCount = 0;
    BluetoothDevice mRemoteDevice;
    //  private SensorManager mSensorManager;
    BluetoothSocket mSocket = null;
    String mStrDelimiter = "\n";
    Thread mWorkerThread = null;
    //  Sensor magnetometer;
    //  float pitch;
    byte[] readBuffer;
    int readBufferPosition;
    String[] temp;



    void checkBluetooth()
    {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.mBluetoothAdapter == null)
        {
            Toast.makeText(getApplicationContext(), "기기가 블루투스를 지원하지 않습니다.",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (!this.mBluetoothAdapter.isEnabled())
        {
            Toast.makeText(getApplicationContext(), "현재 블루투스가 비활성화 상태입니다.",Toast.LENGTH_SHORT).show();
            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 10);
            return;
        }
        selectDevice();
    }



    void connectToSelectedDevice(String paramString)
    {
        this.mRemoteDevice = getDeviceFromBondedList(paramString);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

        try
        {
            this.mSocket = this.mRemoteDevice.createRfcommSocketToServiceRecord(uuid);
            this.mSocket.connect();
            this.mOutputStream = this.mSocket.getOutputStream();
            this.mInputStream = this.mSocket.getInputStream();
            sendData("t");
            return;
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"블루투스 연결 중 오류 발생",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    BluetoothDevice getDeviceFromBondedList(String paramString)
    {
        Iterator localIterator = this.mDevices.iterator();
        BluetoothDevice localBluetoothDevice;
        do
        {
            if (!localIterator.hasNext()) {
                return null;
            }
            localBluetoothDevice = (BluetoothDevice)localIterator.next();
        } while (!paramString.equals(localBluetoothDevice.getName()));
        return localBluetoothDevice;
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
    {
        switch (paramInt1)
        {
        }
        for (;;)
        {
            super.onActivityResult(paramInt1, paramInt2, paramIntent);
            // return;
            if (paramInt2 == -1)
            {
                selectDevice();
            }
            else if (paramInt2 == 0)
            {
                Toast.makeText(getApplicationContext(), "블루투스를 사용할 수 없어 종료합니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        this.mEdit0 = ((Button)findViewById(R.id.degree0));
        this.mEdit45 = ((Button)findViewById(R.id.degree45));
        this.mEdit90 = ((Button)findViewById(R.id.degree90));
        this.mEdit135 = ((Button)findViewById(R.id.degree135));
        this.mEdit180 = ((Button)findViewById(R.id.degree180));

        this.mEdit0.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                MainActivity.this.sendData("j");
            }
        });
        this.mEdit45.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                MainActivity.this.sendData("i");
            }
        });
        this.mEdit90.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                MainActivity.this.sendData("k");
            }
        });
        this.mEdit135.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                MainActivity.this.sendData("o");
            }
        });
        this.mEdit180.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                MainActivity.this.sendData("l");
            }
        });
        checkBluetooth();
    }

    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        //  getMenuInflater().inflate(2131492864, paramMenu);
        return true;
    }

    protected void onDestroy()
    {
        try
        {
           // this.mWorkerThread.interrupt();
            this.mInputStream.close();
            this.mOutputStream.close();
            this.mSocket.close();
            super.onDestroy();
            return;
        }
        catch (Exception localException)
        {
            for (;;) {}
        }
    }

    void selectDevice()
    {
        this.mDevices = this.mBluetoothAdapter.getBondedDevices();
        this.mPairedDeviceCount = this.mDevices.size();
        if (this.mPairedDeviceCount == 0)
        {
            Toast.makeText(getApplicationContext(), "페어링된 장치가 없습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setTitle("블루투스 장치 선택");
        List<String> listItems = new ArrayList<String>();
        for(BluetoothDevice device : mDevices){
            listItems.add(device.getName());
        }
        listItems.add("취소");

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);
        localBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which== MainActivity.this.mPairedDeviceCount){
                    Toast.makeText(MainActivity.this.getApplicationContext(), "연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    MainActivity.this.finish();
                    return;
                }
                else{
                    connectToSelectedDevice(items[which].toString());
                }
            }
        });

        localBuilder.setCancelable(false);
        AlertDialog alert = localBuilder.create();
        alert.show();

    }

    void sendData(String paramString)
    {
        paramString = paramString + this.mStrDelimiter;
        try
        {
            this.mOutputStream.write(paramString.getBytes());
            return;
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "데이터 전송 중 오류 발생.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

