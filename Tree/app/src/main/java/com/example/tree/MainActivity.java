package com.example.tree;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
        Кнопки событий для переключения текста.
     */
    private Button variant1;
    private Button variant2;
    private Button variant3;

    /*
        Поле вывода текста.
     */
    private TextView text;

    private NodeEvent CurrentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Инициализируем переменные
        init();
        //Создание дерева диалогов
        createDialog();
    }

    public void createDialog(){
        //0 уровень (Корень)
        NodeEvent RootDialog = new NodeEvent("Я самый первый диалог!");
        //1 уровень
        NodeEvent LoveDialog = new NodeEvent("Я диалог о любви!");
        RootDialog.AddNodeToEventLeft(LoveDialog);
        NodeEvent WarDialog = new NodeEvent("Я Диалог о войне!");
        RootDialog.AddNodeToEventRight(WarDialog);
        RootDialog.AddNodeToEventCenter(LoveDialog);
        WarDialog.AddNodeToEventCenter(RootDialog);
        LoveDialog.AddNodeToEventCenter(RootDialog);

        CurrentDialog = RootDialog;

        ShowCurrentDialog();
    }

    public void ShowCurrentDialog(){
        text.setText(CurrentDialog.textEvent);
    }

    public void init(){
        variant1 = (Button)findViewById(R.id.BtnVariant1);
        variant2 = (Button)findViewById(R.id.BtnVariant2);
        variant3 = (Button)findViewById(R.id.BtnVariant3);

        text = (TextView)findViewById(R.id.MainText);
    }

    public void Click(View v){
        Button btn = (Button)v;

        if(btn.getId() == R.id.BtnVariant1){
            //Если нажато на вариант 1
            NodeEvent tmp = CurrentDialog.getLeftNode();
            if(tmp != null) {
                CurrentDialog = tmp;
                if(CurrentDialog != null){
                    text.setText(CurrentDialog.textEvent);
                }
            } else {
                NullNode();
            }
        } else if(btn.getId() == R.id.BtnVariant2){
            //Если нажато на вариант 2
            NodeEvent tmp = CurrentDialog.getCenterNode();
            if(tmp != null) {
                CurrentDialog = tmp;
                if(CurrentDialog != null){
                    text.setText(CurrentDialog.textEvent);
                }
            } else {
                NullNode();
            }
        } else if(btn.getId() == R.id.BtnVariant3){
            //Если нажато на вариант 3
            NodeEvent tmp = CurrentDialog.getRightNode();
            if(tmp != null) {
                CurrentDialog = tmp;
                if(CurrentDialog != null){
                    text.setText(CurrentDialog.textEvent);
                }
            } else {
                NullNode();
            }
        }
    }

    public void NullNode(){
        Toast.makeText(this,"Нету вариантов перехода!",Toast.LENGTH_SHORT).show();
    }

    class NodeEvent {
        private NodeEvent variant1 = null;
        private NodeEvent variant2 = null;
        private NodeEvent variant3 = null;

        public String textEvent = "";

        public NodeEvent(String txt){
            this.textEvent = txt;
        }

        //Установка для левого варианта
        public void AddNodeToEventLeft(NodeEvent ev){
            this.variant1 = ev;
        }
        //Установка для центрального варианта
        public void AddNodeToEventCenter(NodeEvent ev){
            this.variant2 = ev;
        }
        //Установка для правого варианта
        public void AddNodeToEventRight(NodeEvent ev){
            this.variant3 = ev;
        }

        /*Возвращает определённое действие*/
        public NodeEvent getLeftNode(){
            return variant1;
        }
        public NodeEvent getCenterNode(){
            return variant2;
        }
        public NodeEvent getRightNode(){
            return variant3;
        }
    }
}
