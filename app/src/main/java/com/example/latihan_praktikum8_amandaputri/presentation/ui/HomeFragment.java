package com.example.latihan_praktikum8_amandaputri.presentation.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import com.example.latihan_praktikum8_amandaputri.R;

public class HomeFragment extends Fragment {

    private static final String CHANNEL_ID = "sensor_channel_id";
    private static final int NOTIFICATION_ID = 100;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnSensor = view.findViewById(R.id.btnSensor);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        createNotificationChannel();

        btnSensor.setOnClickListener(v -> {
            if (accelerometerSensor != null) {
                showNotification("Sensor Aktif", "Sensor akselerometer berhasil diaktifkan.");
            } else {
                showNotification("Sensor Tidak Tersedia", "Sensor akselerometer tidak tersedia di perangkat ini.");
            }
        });

        return view;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Sensor Channel";
            String description = "Channel untuk notifikasi sensor";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_favorit)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(requireContext());
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
