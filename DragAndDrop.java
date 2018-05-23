package dragdrop.br.estacio.fic.dragdrop;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class DragAndDrop extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener {

    private Drawable enterShape;
    private Drawable normalShape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterShape = getResources().getDrawable(R.drawable.bg_over);
        normalShape = getResources().getDrawable(R.drawable.bg);

        findViewById(R.id.myimage1).setOnTouchListener(this);
        findViewById(R.id.myimage2).setOnTouchListener(this);
        findViewById(R.id.myimage3).setOnTouchListener(this);
        findViewById(R.id.myimage4).setOnTouchListener(this);

        findViewById(R.id.topLeft).setOnDragListener(this);
        findViewById(R.id.topRight).setOnDragListener(this);
        findViewById(R.id.bottomLeft).setOnDragListener(this);
        findViewById(R.id.bottomRight).setOnDragListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data, shadowBuilder, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()){
            case DragEvent.ACTION_DRAG_ENTERED:
                // Ao entrar na área que pode fazer o drop
                v.setBackground(enterShape);
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                // Ao sair da área que pode fazer o drop
                v.setBackground(normalShape);
                break;
            case DragEvent.ACTION_DROP:
                // Ao fazer o drop
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                container.addView(view);
                view.setVisibility(View.VISIBLE);
            case DragEvent.ACTION_DRAG_ENDED:
                // Ao terminar de arrastar
                v.setBackground(normalShape);
                View view2 = (View) event.getLocalState();
                view2.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        return true;
    }
}
