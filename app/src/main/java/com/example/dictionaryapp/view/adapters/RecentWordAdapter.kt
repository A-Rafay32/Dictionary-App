package layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionaryapp.R

class RecentWordAdapter(private val recentWordList : List<String>) : RecyclerView.Adapter<RecentWordsHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentWordsHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recent_word_view,parent , false)
        return RecentWordsHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recentWordList.size
    }

    override fun onBindViewHolder(holder: RecentWordsHolder, position: Int) {
        val currentItem = recentWordList[position]
        holder.recentWord.text = currentItem.toString()
    }


}
class RecentWordsHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val recentWord : TextView = itemView.findViewById(R.id.RecentWords)

}