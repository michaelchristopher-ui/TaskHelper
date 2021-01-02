//
// Created by others on 20/12/24.
//

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_michaelchristophermanullang_tugastengahsemester_fragment_AkhirSemesterFragment_fetchTxtLen(
        JNIEnv *env, jobject thiz, jstring txt_) {
    const char *txt = env->GetStringUTFChars(txt_, 0);
    int len = strlen(txt);

    return len;
}