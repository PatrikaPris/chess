// Example Android Java code for animating a "New Game" button
// The button remains hidden during the game
// The button appears with animation when a player wins

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ButtonAnimationActivity extends AppCompatActivity {

    private Button newGameButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameButton = findViewById(R.id.newGameButton);
        resultText = findViewById(R.id.resultText);

        // Hide button until game is finished
        newGameButton.setVisibility(View.GONE);

        newGameButton.setOnClickListener(v -> {
            startNewGame();
        });
    }

    // Call this method when a player wins
    public void onGameWon(boolean whiteWinner) {

        if (whiteWinner) {
            resultText.setText("🏆 WHITE PLAYER WINS 🏆");
        } else {
            resultText.setText("🏆 BLACK PLAYER WINS 🏆");
        }

        resultText.setVisibility(View.VISIBLE);

        // Show New Game button
        newGameButton.setVisibility(View.VISIBLE);

        // Play winner animation
        playWinAnimation(resultText);

        // Play button animation
        playButtonAnimation(newGameButton);
    }

    // Restart game logic
    private void startNewGame() {

        resultText.setVisibility(View.GONE);
        newGameButton.setVisibility(View.GONE);

        // Reset board here
        // resetBoard();

        // Start next match here
        // startMatch();
    }

    // Animation for winner text
    private void playWinAnimation(View view) {

        AlphaAnimation fadeIn = new AlphaAnimation(0f, 1f);
        fadeIn.setDuration(700);

        ScaleAnimation zoom = new ScaleAnimation(
                0.5f, 1.2f,
                0.5f, 1.2f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );

        zoom.setDuration(700);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(fadeIn);
        set.addAnimation(zoom);

        view.startAnimation(set);
    }

    // Animation for New Game button
    private void playButtonAnimation(View view) {

        ScaleAnimation pulse = new ScaleAnimation(
                0.8f, 1.1f,
                0.8f, 1.1f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );

        pulse.setDuration(300);
        pulse.setRepeatCount(2);
        pulse.setRepeatMode(ScaleAnimation.REVERSE);

        view.startAnimation(pulse);
    }
}
// FullScreenWinOverlay.java
// Full-screen win overlay animation
// Shows winner message, dark background fade and animated New Game button

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class FullScreenWinOverlay {

    public static void show(
            Activity activity,
            boolean whiteWinner,
            Runnable newGameAction
    ) {

        FrameLayout root = activity.findViewById(android.R.id.content);

        FrameLayout overlay = new FrameLayout(activity);
        overlay.setBackgroundColor(0xDD000000);

        overlay.setLayoutParams(
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        TextView winnerText = new TextView(activity);

        winnerText.setText(
                whiteWinner
                        ? "🏆 WHITE PLAYER WINS 🏆"
                        : "🏆 BLACK PLAYER WINS 🏆"
        );

        winnerText.setTextSize(32);
        winnerText.setTextColor(Color.WHITE);
        winnerText.setGravity(Gravity.CENTER);

        FrameLayout.LayoutParams winnerParams =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        winnerParams.gravity = Gravity.CENTER_HORIZONTAL;
        winnerParams.topMargin = 350;

        winnerText.setLayoutParams(winnerParams);

        Button newGameButton = new Button(activity);

        newGameButton.setText("NEW GAME");

        FrameLayout.LayoutParams buttonParams =
                new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        buttonParams.gravity = Gravity.CENTER_HORIZONTAL;
        buttonParams.topMargin = 550;

        newGameButton.setLayoutParams(buttonParams);

        newGameButton.setOnClickListener(v -> {

            root.removeView(overlay);

            if (newGameAction != null) {
                newGameAction.run();
            }
        });

        overlay.addView(winnerText);
        overlay.addView(newGameButton);

        root.addView(overlay);

        playOverlayAnimation(overlay);
        playWinnerAnimation(winnerText);
        playButtonAnimation(newGameButton);
    }

    private static void playOverlayAnimation(View view) {

        AlphaAnimation fadeIn =
                new AlphaAnimation(0f, 1f);

        fadeIn.setDuration(600);

        view.startAnimation(fadeIn);
    }

    private static void playWinnerAnimation(View view) {

        AnimationSet set = new AnimationSet(true);

        AlphaAnimation fade =
                new AlphaAnimation(0f, 1f);

        fade.setDuration(800);

        ScaleAnimation scale =
                new ScaleAnimation(
                        0.3f, 1.2f,
                        0.3f, 1.2f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f
                );

        scale.setDuration(800);

        set.addAnimation(fade);
        set.addAnimation(scale);

        view.startAnimation(set);
    }

    private static void playButtonAnimation(View view) {

        ScaleAnimation pulse =
                new ScaleAnimation(
                        0.8f, 1.1f,
                        0.8f, 1.1f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f
                );

        pulse.setDuration(400);
        pulse.setRepeatCount(3);
        pulse.setRepeatMode(ScaleAnimation.REVERSE);

        view.startAnimation(pulse);
    }
}
FullScreenWinOverlay.show(
    this,
    true, // true = white wins, false = black wins
    () -> {
        startNewGame();
    }
);
