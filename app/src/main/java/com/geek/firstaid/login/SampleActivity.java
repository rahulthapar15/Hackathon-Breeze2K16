package com.geek.firstaid.login;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;

import com.geek.firstaid.R;
import com.geek.firstaid.activities.LoginActivity;
import com.geek.firstaid.login.checks.CookieThumperSample;
import com.geek.firstaid.login.checks.TransitionHelper;
import com.geek.firstaid.login.checks.TranslateAnimationHelper;

import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.RevealColorView;
import su.levenetc.android.textsurface.Debug;
import su.levenetc.android.textsurface.TextSurface;

/**
 * Created by Eugene Levenetc.
 */
public class SampleActivity extends AppCompatActivity {

	/**
	 * {@link TransitionHelper} class object used to help do scene transition
	 */
	private TransitionHelper mTransitionHelper;

	/**
	 * {@link ImageButton} object used to perform translate animations.
	 */
	private ImageButton mCircularAnimatingView;

	/**
	 * {@link Toast} object used by this activity.
	 */
	private Toast mToast;

	/**
	 * Scale Animation duration
	 */
	private static final long SCALE_ANIMATION_DURATION = 800;

	/**
	 * Scale Animation starting offset
	 */
	private static final long SCALE_ANIMATION_STARTOFFSET = 2500;

	/**
	 * Animated logo starting offset
	 */
	private static final long ANIMATED_LOGO_STARTOFFSET = 0000;

	/**
	 * Translate Animation starting offset
	 */
	private static final long TRANSLATE_TO_BOTTOM_START_OFFSET = SCALE_ANIMATION_STARTOFFSET + SCALE_ANIMATION_DURATION + 100;

	/**
	 * Reveal Animation starting offset
	 */
	private static final long REVEAL_ANIMATION_START_OFFSET = TRANSLATE_TO_BOTTOM_START_OFFSET + 000;

	/**
	 * Called when the activity is starting.
	 * <p />
	 * This is where most initialization should go:
	 * calling {@link #setContentView(int)} to inflate the activity's UI,
	 * using {@link #findViewById} to programmatically interact with widgets
	 * in the UI.
	 *
	 * @param savedInstanceState If the activity is being re-initialized after
	 *     previously being shut down then this Bundle contains the data it most
	 *     recently supplied in {@link #onSaveInstanceState}.
	 */

	private TextSurface textSurface;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sample_activity);
		textSurface = (TextSurface) findViewById(R.id.text_surface);

		textSurface.postDelayed(new Runnable() {
			@Override public void run() {
				show();

			}
		}, 1000);

		findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				show();
			}
		});

		CheckBox checkDebug = (CheckBox) findViewById(R.id.check_debug);
		checkDebug.setChecked(Debug.ENABLED);
		checkDebug.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Debug.ENABLED = isChecked;
				textSurface.invalidate();
			}
		});

		extendImageToDrawOverStatusBar();

		/** Initialize local variables **/
		mTransitionHelper = new TransitionHelper();
		mCircularAnimatingView = (ImageButton)findViewById(R.id.scene_transition_between_elements_go_button_and_login_register_tile);


		/** Then, bouncing ball needs to go visible on screen **/
		//popOutBall();

		/** Now, need to setup bouncing animation of bouncing ball **/
		//setupBouncingOfBall();

		/** Finally, setup reveal color animation **/
	//	setupRevealEffect();


		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				/***
				 * Start your activity here
				 */

				Intent intent = new Intent(SampleActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
//				Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
			}
		},4000);
	}

	public void setupRevealEffect(){

		/** Does reveal animation after some start offset duration **/
		final Handler handler = new Handler();
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						reveal((RevealColorView) findViewById(R.id.reveal), findViewById(R.id.dummy_view_for_revealing_animation));
					}
				});
			}
		}, REVEAL_ANIMATION_START_OFFSET);



	}

	public void popOutBall(){

		/** Hide bouncing ball **/
		mCircularAnimatingView.setScaleX(0.0f);
		mCircularAnimatingView.setScaleY(0.0f);

		/** Pop out animating ball with scale animation **/
		ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(mCircularAnimatingView,"scaleX", 0.0f, 1.0f);
		scaleXAnimation.setDuration(SCALE_ANIMATION_DURATION);
		ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(mCircularAnimatingView,"scaleY", 0.0f, 1.0f);
		scaleYAnimation.setDuration(SCALE_ANIMATION_DURATION);

		/** Start scaling animation **/
		scaleXAnimation.setStartDelay(SCALE_ANIMATION_STARTOFFSET);
		scaleYAnimation.setStartDelay(SCALE_ANIMATION_STARTOFFSET);
		scaleYAnimation.start();
		scaleXAnimation.start();

		/** Show bouncing ball **/
		final Handler handler = new Handler();
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						mCircularAnimatingView.setVisibility(View.VISIBLE);
					}
				});
			}
		}, SCALE_ANIMATION_STARTOFFSET);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
	public void setupBouncingOfBall(){

		/** Accelerate ball to bottom **/
		TranslateAnimationHelper translateToBottom = new TranslateAnimationHelper(mCircularAnimatingView, 0f, 0f, 0f, getWindowManager().getDefaultDisplay().getHeight()/2.2f, TRANSLATE_TO_BOTTOM_START_OFFSET, new AccelerateInterpolator());

		translateToBottom.setTranslateAnimationEndListener(new TranslateAnimationHelper.TranslateAnimationEndListener() {

			@TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
			@Override
			public void onTranslateAnimationEnd() {

				/** De-accelerate ball back to original position **/
				TranslateAnimationHelper backToPositionTranslateAnimation = new TranslateAnimationHelper(mCircularAnimatingView, 0f, 0f, getWindowManager().getDefaultDisplay().getHeight() / 2.2f, 0f, new DecelerateInterpolator());

				backToPositionTranslateAnimation.setTranslateAnimationEndListener(new TranslateAnimationHelper.TranslateAnimationEndListener() {

					@Override
					public void onTranslateAnimationEnd() {

						mCircularAnimatingView.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_forward_white_24dp));
						mCircularAnimatingView.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View v) {
								goClicked(v);
							}
						});
					}
				});
			}
		});
	}

	private void show() {


		textSurface.reset();
		CookieThumperSample.play(textSurface, getAssets());

	}
	public void extendImageToDrawOverStatusBar(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window w = getWindow();
			w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		}
	}


	/**
	 * This function gets triggered whenever presses forward arrow button present on main
	 * ui to reach login/register ui screen.
	 *
	 * @param v View with forward arrow, leading to login/register ui screen.
	 */
	public void goClicked(View v){
		mTransitionHelper.doSceneTransition(R.id.root_container, R.layout.login_scene_2, SampleActivity.this);
	}

	/**
	 * This method returns the x,y coordinates of the point from where
	 * reveal animation will get started.
	 *
	 * @param src Reveal animation view, on this view reveal animation
	 *            will be triggered.
	 * @param target This is the view, which helps find the center of
	 *               reveal animation view, located at bottom of screen.
	 * @return {@link Point} object whose coordinates are going to be
	 *         center for reveal effect.
	 */
	private Point getLocationInView(View src, View target) {

		final int[] l0 = new int[2];
		src.getLocationOnScreen(l0);

		final int[] l1 = new int[2];
		target.getLocationOnScreen(l1);

		l1[0] = l1[0] - l0[0] + target.getWidth() / 2;
		l1[1] = l1[1] - l0[1] + target.getHeight() / 2;

		return new Point(l1[0], l1[1]);
	}

	/**
	 * Given the view, it calculates the center of reveal animation,
	 * and starts reveal animation.
	 *
	 * @param v This is the view, which helps find the center of
	 *          reveal animation view, located at bottom of screen.
	 */
	public void reveal(RevealColorView revealColorView, View v){
		final Point p = getLocationInView(revealColorView, v);
		revealColorView.reveal(p.x, p.y, getResources().getColor(R.color.colorPrimaryDark), v.getHeight() / 2, 500, null);
	}

	/**
	 * This function gets triggered whenever submit button is clicked,
	 * Clears previously created {@link Toast} object, and creates a
	 * new {@link Toast} object and displays on screen.
	 *
	 * @param v submit button view object
	 */
	public void submitClicked(View v){

		if(mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(SampleActivity.this,"This is a demo login/register ui", Toast.LENGTH_SHORT);
		mToast.show();
	}

}