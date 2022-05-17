
import android.annotation.SuppressLint
import android.content.Context;
import android.content.SharedPreferences;

class SharedPrefs(private val context:Context?){
    fun <T>check(sFile: String,keyS: String,defaultS: T):T{
        if(context==null)return defaultS
        val sPref:SharedPreferences= context.getSharedPreferences(sFile,Context.MODE_PRIVATE)
        return when(defaultS){
            is String->sPref.getString(keyS,defaultS) as T
            is Int-> sPref.getInt(keyS,defaultS) as T
            is Long-> sPref.getLong(keyS,defaultS) as T
            is Boolean-> sPref.getBoolean(keyS,defaultS) as T
            else-> defaultS
        }
    }
    @SuppressLint("ApplySharedPref")
    fun save(sFile:String, sKey:String, sValue:Any, saveImmediately:Boolean=false){
        if(context==null)return
        val sharedPreferences:SharedPreferences = context.getSharedPreferences(sFile, Context.MODE_PRIVATE);
        val editor:SharedPreferences.Editor = sharedPreferences.edit();
        when(sValue) {
            is String-> editor.putString(sKey, sValue)
            is Int->editor.putInt(sKey, sValue)
            is Long-> editor.putLong(sKey, sValue)
            is Boolean->editor.putBoolean(sKey, sValue)
        }
        if(saveImmediately){editor.commit()}
        else{ editor.apply()}
    }
}
