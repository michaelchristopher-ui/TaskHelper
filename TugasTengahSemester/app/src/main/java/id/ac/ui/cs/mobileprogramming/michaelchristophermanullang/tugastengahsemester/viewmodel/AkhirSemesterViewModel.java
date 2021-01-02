package id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.ac.ui.cs.mobileprogramming.michaelchristophermanullang.tugastengahsemester.model.AkhirSemesterModel;

public class AkhirSemesterViewModel extends ViewModel {
    private AkhirSemesterModel akhirSemesterModel;

    public AkhirSemesterViewModel(){
        akhirSemesterModel = new AkhirSemesterModel();
    }

    public void setText(CharSequence input) {
        //text.setValue(input) ;
        System.out.println("setText in ViewModel");
        akhirSemesterModel.setText(input);
    }

    public LiveData<CharSequence> getText() {
        return akhirSemesterModel.getText();
    }
}
