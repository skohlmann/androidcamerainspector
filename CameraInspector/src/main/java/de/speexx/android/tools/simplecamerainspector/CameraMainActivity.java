package de.speexx.android.tools.simplecamerainspector;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CameraMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_main);

        try {

            final CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

            final StringBuilder sb = new StringBuilder("Camera Infos:\n=================================\n");
            for (final String cId : cameraManager.getCameraIdList()) {
                sb.append("CameraID: ").append(cId).append("\n---------------\n");
                final CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cId);
                sb.append("LENS_FACING: ").append(lenseFacing(cameraCharacteristics)).append("\n");
                sb.append("SENSOR_INFO_WHITE_LEVEL: ").append(cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_WHITE_LEVEL)).append("\n");
                sb.append("=================================\n");
            }
            final TextView textView = (TextView)findViewById(R.id.camera_text_view);
            textView.setText(sb);

        } catch (final CameraAccessException e) {
            e.printStackTrace();
        }
    }

    final String lenseFacing(final CameraCharacteristics cameraCharacteristics) {
        final Integer facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
        if (facing == CameraCharacteristics.LENS_FACING_BACK) {
            return ("back");
        } else if (facing == CameraCharacteristics.LENS_FACING_FRONT) {
            return "front";
        } else if (facing == CameraCharacteristics.LENS_FACING_EXTERNAL) {
            return "extern";
        }
        return "unknown";

    }
}
