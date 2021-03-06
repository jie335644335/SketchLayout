package com.lvshou.sketchlayout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.JsonReader;
import android.util.Log;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by georgeyang1024 on 2017/12/22.
 */
public class SketchLayout extends ConstraintLayout {
    public SketchLayout(Context context) {
        super(context);
    }

    public SketchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SketchLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int showIndex = 3;//需要排版的界面index
    public void show() {
        try {
            //3.0设计/登录注册/index.html#artboard9
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open("test.json"));
            JsonReader reader = new JsonReader(inputReader);
            reader.beginObject();
            while(reader.hasNext()){
                String keyName = reader.nextName();
                Log.d("test",keyName);
                if (TextUtils.equals(keyName,"artboards")) {
                    reader.beginArray();
                    int index = 0;
                    while (reader.hasNext()) {
                        if (index==showIndex) {
                            startLayout(reader);
                        }
                        index++;
                    }
                    reader.endArray();
                }
                reader.skipValue();
            }
            reader.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLayout(JsonReader jsonReader) throws Exception {
        List<Layer> layer = null;

        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String keyName = jsonReader.nextName();
            if (TextUtils.equals("notes",keyName)) {
                jsonReader.skipValue();
                continue;
            } else if (TextUtils.equals("layers",keyName)) {
                jsonReader.beginArray();
                layer = JsonReaderUtil.paresArrayByTagClass(jsonReader, Layer.class);
                Log.d("test","layer:" + layer.size());
                jsonReader.endArray();
            } else {
                String name = jsonReader.nextString();
                Log.d("test",keyName + ">>" + name);
            }
        }
        jsonReader.endObject();

        //拿到所有

        //TODO
        while (layer.size()>0){
            List<Layer> removeList = new ArrayList<>();
            int left,right,top,bootom;
            for (Layer layoutLayout:layer) {

            }
        }
    }
}
