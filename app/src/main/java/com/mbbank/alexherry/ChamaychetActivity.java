package com.mbbank.alexherry;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.mbbank.alexherry.databinding.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class ChamaychetActivity extends AppCompatActivity {
	
	private ChamaychetBinding binding;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = ChamaychetBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
	}
	
	private void initializeLogic() {
		final String CANH_BAO_1 = "CHA_MAY_CHET_HA";
		final String CANH_BAO_2 = "MA_DI_CRACK_APP_TAO_VAY";
		final String CANH_BAO_3 = "DOI_LAM_HA_DI_CRACK_VE_BAN_A";
		final String LOI_NGUYEN = "SAI_LAM_ROI_LH_TELEGRAM_ALEXHERRY_SEEK_DI_NHE";
		final String KEY = "HaQuynhAnh@@__@@";
		final String TELEGRAM = "https://t.me/nguyennhatit";
		final String MADEBY = "NGUYEN_MINH_NHAT";
		
		android.util.Log.e("CANH_BAO_CRACK", CANH_BAO_1 + " | " + LOI_NGUYEN);
		
	}
	
}