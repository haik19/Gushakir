package com.gushakir.hs.gushakir;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends FragmentActivity {

	private Button btn5x5;
	private Button btn8x8;
	private GridLayout gridLayout;
	private MusicManager musicManager;
	private List<View> viewList;
	private Button startBtn;
	private Random random;
	private CountDownTimer countDownTimer;
	private boolean isStarted;
	private ConstraintLayout constraintLayout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameUtils.hideSystemUI(getWindow());
		setContentView(R.layout.activity_main);
		getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
			@Override
			public void onSystemUiVisibilityChange(int i) {
				GameUtils.hideSystemUI(getWindow());
			}
		});
		constraintLayout = findViewById(R.id.constraint_layout);
		gridLayout = findViewById(R.id.grid_layout);
		musicManager = MusicManager.getMusicManager(this);
		viewList = new ArrayList<>();
		startBtn = findViewById(R.id.start_btn);
		random = new Random();
		countDownTimer = new CountDownTimer(Integer.MAX_VALUE, 800) {
			@Override
			public void onTick(long millisUntilFinished) {
				int i = random.nextInt(viewList.size());
				viewList.get(i).callOnClick();
			}

			@Override
			public void onFinish() {
			}
		};
		startBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (isStarted) {
					countDownTimer.cancel();
					startBtn.setText("Start");
					isStarted = false;
				} else {
					countDownTimer.start();
					startBtn.setText("Pause");
					isStarted = true;
				}
			}
		});

		btn5x5 = findViewById(R.id.btn5x5);
		btn8x8 = findViewById(R.id.btn8x8);
		addSquareBlocks(5,5);

		btn5x5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				addSquareBlocks(5,5);
			}
		});

		btn8x8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				addSquareBlocks(8,8);
			}
		});
	}


	private void addSquareBlocks(int row, int column) {
		if (gridLayout == null) {
			return;
		}
		viewList.clear();
		if (gridLayout.getChildCount() > 0) {
			gridLayout.removeAllViews();
		}
		gridLayout.setRowCount(row);
		gridLayout.setColumnCount(column);

		int blocksCount = row * column;
		int deviceWidth = GameUtils.getDeviceWidth(this);
		int blockHeight;
		int blockWidth = blockHeight = deviceWidth / row;
		gridLayout.getLayoutParams().height = row * blockHeight;

		for (int i = 0; i < blocksCount; i++) {
			View view = new View(this);
			view.setLayoutParams(new LinearLayout.LayoutParams(blockWidth, blockHeight));
			view.getLayoutParams().width = blockWidth;
			view.getLayoutParams().height = blockHeight;
			view.setOnClickListener(onClickListener);
			view.setBackgroundResource(R.drawable.square_item_gradient_background);
			gridLayout.addView(view);
			viewList.add(view);
		}
	}

	private View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(final View view) {
			if (view.getVisibility() == View.VISIBLE) {
				musicManager.playHideBlockMusic();
				view.animate()
						.translationY(view.getHeight())
						.alpha(0.0f)
						.setDuration(800)
						.setListener(new AnimatorListenerAdapter() {
							@Override
							public void onAnimationEnd(Animator animation) {
								super.onAnimationEnd(animation);
								view.setVisibility(View.INVISIBLE);
							}
						});
			}
		}
	};

}
