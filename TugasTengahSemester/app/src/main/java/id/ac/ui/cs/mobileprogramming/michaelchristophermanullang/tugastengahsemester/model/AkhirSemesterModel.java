package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public class AkhirSemesterModel {

    @Nullable
    private MutableLiveData<CharSequence> text;
    public AkhirSemesterModel(){
        text = new MutableLiveData<>();
    }

    public void setText(CharSequence input){
        System.out.println("setText in Model");
        text.setValue(input);
    }

    public MutableLiveData<CharSequence> getText(){
        return text;
    }
}
