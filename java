// Example Android Java code for animating a "New Game" button
// This snippet can be placed inside an Android Activity or Fragment.
// It applies a pulse animation when the button appears or is pressed.

import android.os.Bundle;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ButtonAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newGameButton = findViewById(R.id.newGameButton);

        // Apply pulse animation when the button first appears
        applyPulseAnimation(newGameButton);

        // Apply pulse animation on press
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyPulseAnimation(newGameButton);
                // Add your new game logic here
            }
        });
    }

    // Pulse animation
    private void applyPulseAnimation(View view) {
        ScaleAnimation pulse = new ScaleAnimation(
                0.9f, 1.05f,   // Start and end scale X
                0.9f, 1.05f,   // Start and end scale Y
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );

        pulse.setDuration(200);
        pulse.setRepeatCount(1);
        pulse.setRepeatMode(ScaleAnimation.REVERSE);

        view.startAnimation(pulse);
    }
}
