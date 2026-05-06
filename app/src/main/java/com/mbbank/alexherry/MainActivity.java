package com.mbbank.alexherry;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.mbbank.alexherry.databinding.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private MainBinding binding;
	
	private Intent alexherry = new Intent();
	private TimerTask odaycosharene;
	private AlertDialog.Builder nmn;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		nmn = new AlertDialog.Builder(this);
		
		binding.textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				alexherry.setData(Uri.parse("https://t.me/lienminhflukez"));
				startActivity(alexherry);
			}
		});
		
		binding.button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				EditText stk = findViewById(R.id.stk);
				EditText sotien = findViewById(R.id.sotien);
				EditText sodu = findViewById(R.id.sodu);
				EditText stknguoinhan = findViewById(R.id.stknguoinhan);
				EditText tenguoinhan = findViewById(R.id.tenguoinhan);
				EditText noidung = findViewById(R.id.noidung);
				Button button1 = findViewById(R.id.button1);
				
				sotien.addTextChangedListener(new TextWatcher() {
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						if (s.toString().trim().startsWith("-")) {
							tenguoinhan.setVisibility(View.VISIBLE);
						} else {
							tenguoinhan.setVisibility(View.GONE);
							tenguoinhan.setText("");
						}
					}
					
					@Override
					public void afterTextChanged(Editable s) {}
				});
				
				button1.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						String stk_txt = stk.getText().toString().trim();
						String sotien_txt = sotien.getText().toString().trim();
						String sodu_txt = sodu.getText().toString().trim();
						String stknguoinhan_txt = stknguoinhan.getText().toString().trim();
						String tennguoinhan_txt = tenguoinhan.getText().toString().trim();
						String noidung_txt = noidung.getText().toString().trim();
						
						boolean isPlus = sotien_txt.startsWith("+");
						boolean isMinus = sotien_txt.startsWith("-");
						
						if (stk_txt.isEmpty() || sotien_txt.isEmpty() || sodu_txt.isEmpty() || noidung_txt.isEmpty()) {
							LayoutInflater Inflater = getLayoutInflater();
							View InfView = getLayoutInflater().inflate(R.layout.haquynhanh, null);
							LinearLayout border = (LinearLayout) InfView.findViewById(R.id.border);
							LinearLayout line = (LinearLayout) InfView.findViewById(R.id.line);
							TextView title = (TextView) InfView.findViewById(R.id.title);
							TextView content = (TextView) InfView.findViewById(R.id.content);
							ImageView image_logo = (ImageView) InfView.findViewById(R.id.image_logo);
							Toast ToastName = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);
							ToastName.setView(InfView);
							image_logo.setImageResource(R.drawable.warn);
							border.setElevation((float) 2);
							title.setTextColor(Color.parseColor("#FFC122"));
							title.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/alexherry.ttf"), 1);
							content.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/alexherry.ttf"), 0);
							_RoundAndBorder(border, "#ffffff", 0, "#00000000", 8);
							_RoundAndBorder(line, "#FFC122", 0, "#00000000", 360);
							title.setText("Cảnh Báo!");
							content.setText("Vui lòng nhập đầy đủ thông tin cần thiết.");
							ToastName.show();
							return;
						}
						
						if (!isPlus && !isMinus) {
							sotien.setError("Số tiền phải bắt đầu bằng + hoặc -");
							return;
						}
						
						if (isMinus && tennguoinhan_txt.isEmpty()) {
							tenguoinhan.setError("Vui lòng nhập tên người nhận");
							return;
						}
						
						String maskedStk = stk_txt;
						if (stk_txt.length() > 5) {
							String firstTwo = stk_txt.substring(0, 2);
							String lastThree = stk_txt.substring(stk_txt.length() - 3);
							maskedStk = firstTwo + "xxxx" + lastThree;
						}
						
						java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yy HH:mm", java.util.Locale.getDefault());
						String time = sdf.format(new java.util.Date());
						String transId = "QABRJC" + (int) (Math.random() * 9000 + 1000);
						String messageContent;
						
						if (isPlus) {
							messageContent = "TK " + maskedStk +
							"|GD: " + sotien_txt + "VND " + time +
							" |SD: " + sodu_txt + "VND" +
							"|ND: " + transId + " APPMB1 1 " + noidung_txt;
						} else {
							messageContent = "TK " + maskedStk +
							"|GD: " + sotien_txt + "VND " + time +
							" |SD: " + sodu_txt + "VND" +
							"|DEN: " + tennguoinhan_txt +
							"|ND: " + transId + " APPMB1 1 " + noidung_txt;
						}
						
						_showNotification("Thông báo biến động số dư", messageContent);
					}
				});
			}
		});
	}
	
	private void initializeLogic() {
		if (android.os.Build.VERSION.SDK_INT >= 33) {
			if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
			!= PackageManager.PERMISSION_GRANTED) {
				
				requestPermissions(
				new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
				1001
				);
			}
		}
		FileUtil.makeDir(".alexherryodaycosharene");
		_showNoticeDialog();
		odaycosharene = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						binding.alexherryqa.setVisibility(View.GONE);
						binding.qa.setVisibility(View.GONE);
						odaycosharene = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										binding.hqa.setVisibility(View.VISIBLE);
									}
								});
							}
						};
						_timer.schedule(odaycosharene, (int)(200));
					}
				});
			}
		};
		_timer.schedule(odaycosharene, (int)(5000));
	}
	
	public void _showNotification(final String _title, final String _content) {
		
		NotificationManager notificationManager =
		(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		String channelId = "multi_notify_channel";
		
		if (android.os.Build.VERSION.SDK_INT >= 26) {
			NotificationChannel channel = new NotificationChannel(
			channelId,
			"Multi Notification",
			NotificationManager.IMPORTANCE_HIGH
			);
			channel.enableLights(true);
			channel.enableVibration(true);
			notificationManager.createNotificationChannel(channel);
		}
		
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		PendingIntent pendingIntent = PendingIntent.getActivity(
		this,
		0,
		intent,
		PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
		);
		
		androidx.core.app.NotificationCompat.BigTextStyle bigTextStyle =
		new androidx.core.app.NotificationCompat.BigTextStyle()
		.setBigContentTitle(_title)
		.bigText(_content);
		
		androidx.core.app.NotificationCompat.Builder builder =
		new androidx.core.app.NotificationCompat.Builder(this, channelId)
		.setSmallIcon(R.mipmap.ic_launcher)
		.setContentTitle(_title)
		.setContentText(_content)
		.setStyle(bigTextStyle)
		.setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
		.setAutoCancel(true)
		.setContentIntent(pendingIntent);
		
		// MULTI notification – KHÔNG ghi đè
		int notifyId = (int) System.currentTimeMillis();
		notificationManager.notify(notifyId, builder.build());
	}
	
	
	public void _showNoticeDialog() {
		SharedPreferences sp =
		getSharedPreferences("notice_time", MODE_PRIVATE);
		
		long lastCloseTime = sp.getLong("last_close", 0);
		long currentTime = System.currentTimeMillis();
		
		long twoHours = 2 * 60 * 60 * 1000;
		
		if (currentTime - lastCloseTime < twoHours) {
			return;
		}
		
		AlertDialog.Builder nmn = new AlertDialog.Builder(this);
		Intent alexherry = new Intent(Intent.ACTION_VIEW);
		
		nmn.setTitle("Thông Báo !");
		nmn.setIcon(R.drawable.flukez);
		nmn.setMessage(
		"- Cảm Ơn Bạn Đã Sử Dụng Ứng Dụng Của Chúng Tôi.\n"
		+ "- Ứng Dụng Được Tạo Ra Mục Đích Hợp Tập, test app của Anhemcrown chúng tôi\n"
		+ "- Vui Lòng Không Sử Dụng Mục Đích Bất Hợp Pháp.\n"
		+ "- Tham gia máy chủ Discord của chúng tôi"
		);
		
		nmn.setPositiveButton("Tham gia máy chủ", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				alexherry.setData(Uri.parse("https://discord.gg/snknfdQdR"));
				startActivity(alexherry);
			}
		});
		
		nmn.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				sp.edit()
				.putLong("last_close", System.currentTimeMillis())
				.apply();
			}
		});
		
		nmn.setCancelable(false);
		nmn.create().show();
	}
	
	
	public void _AnimationLogo(final View _view, final String _propertyName, final double _value, final double _duration) {
		ObjectAnimator anim = new ObjectAnimator();
		
		anim.setTarget(_view);
		
		anim.setPropertyName(_propertyName);
		
		anim.setFloatValues((float)_value);
		
		anim.setDuration((long)_duration);
		
		anim.setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator());
		
		anim.start();
	}
	
	
	public void _RoundAndBorder(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _clickAnimation(final View _view) {
		ScaleAnimation fade_in = new ScaleAnimation(0.9f, 1f, 0.9f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
		fade_in.setDuration(300);
		fade_in.setFillAfter(true);
		_view.startAnimation(fade_in);
	}
	
}
