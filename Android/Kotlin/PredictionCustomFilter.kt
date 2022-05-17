import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Filter
import android.widget.TextView
import java.util.*
import kotlin.collections.ArrayList

object PredictionCustomFilter {

    class PredictionCustomFilter(
        private val mContext: Context,
        private val viewResourceId: Int,
        private val items: List<String?>,
	private val idOfTextView:Int
    ) :
        ArrayAdapter<String?>(mContext, viewResourceId, items) {
        private val itemsAll: List<String?> = items
        private val suggestions: ArrayList<String> = ArrayList()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var v = convertView
            if (v == null) {
                val vi =
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                v = vi.inflate(viewResourceId, parent, false)
            }
            val text = items[position]
            if (text != null) {
                val textLabel = v?.findViewById<View>(idOfTextView) as TextView?
                textLabel?.text = text
            }
            return v!!
        }

        override fun getFilter(): Filter {
            return nameFilter
        }
	private var nameFilter: Filter = object : Filter() {
            override fun convertResultToString(resultValue: Any?): String {
                return resultValue?.toString() ?: ""
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return if (constraint != null) {
                    suggestions.clear()
                    val notStartsWith = ArrayList<String>()
                    for (string in itemsAll) {
                        if (string?.lowercase(Locale.getDefault())?.startsWith(
                                constraint.toString()
                                    .lowercase(Locale.getDefault())
                            ) == true
                        ) {
                            suggestions.add(string)
                        } else if (string?.lowercase(Locale.getDefault())
                                ?.contains(
                                    constraint.toString().lowercase(Locale.getDefault())
                                ) == true
                        ) {
                            notStartsWith.add(string)
                        }
                    }
                    if (notStartsWith.isNotEmpty()) {
                        suggestions.addAll(notStartsWith)
                    }
                    val filterResults = FilterResults()
                    filterResults.values = suggestions
                    filterResults.count = suggestions.size
                    filterResults
                } else {
                    FilterResults()
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                val filteredList = results?.values as? List<*>
                if (results?.count ?: 0 > 0) {clear()
                    if (filteredList == null) return
                    for (c in filteredList) {
                        add(c as String?)
                    }
                    notifyDataSetChanged()
                }
            }
        }

    }

}
