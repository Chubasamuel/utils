import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun getDateLabel(date:Date,outputFormat:String="EEEE, MMMM d, yyyy  hh:mm aa",defaultValue:String=""): String {
        return try {
            SimpleDateFormat(outputFormat, Locale.getDefault()).format(date)
        } catch (e: Exception) {
            e.printStackTrace(); defaultValue
        }
}
}
