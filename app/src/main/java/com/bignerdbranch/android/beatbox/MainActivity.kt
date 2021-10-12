package com.bignerdbranch.android.beatbox

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdbranch.android.beatbox.databinding.ActivityMainBinding
import com.bignerdbranch.android.beatbox.databinding.ListItemSoundBinding

/**
 * The Main Activity for the application
 */
class MainActivity : AppCompatActivity() {
    /**
     * property that references the BeatBox class: com.bignerdbranch.android.beatbox.BeatBox
     */
    private lateinit var beatBox: BeatBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region Instantiate beatBox property
        beatBox = BeatBox(assets)
        //endregion

        /**
         *A property used bind MainActivity.kt to its resource layout activity_main.xml
         */
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //region Modify properties of RecyclerView: Using 'binding' property to access a view with @+id/recycler_view
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = SoundAdapter(beatBox.sounds)
        }
        //endregion

    }
/**This override method performs actions when the user exit the app by releasing memory of clicking the back Button
 *@see  androidx.appcompat.app.AppCompatActivity.onDestroy()
 */
    override fun onDestroy() {
        super.onDestroy()
        beatBox.release()
    }

    /**
     * RecyclerView ViewHolder:The ViewHolder for the RecyclerView in activity_main.xml
     * The viewholder xml layout is list_item_sound.xml
     */
    private inner class SoundHolder(private val binding: ListItemSoundBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /**
         * Hook up the viewModel to the SoundViewModel()
         */
        init {
            binding.viewModel = SoundViewModel(beatBox)
        }

        /**
         * This method binds the data in the viewModel to the data sent to this SoundHolder class
         * @param sound  an object of the Sound.kt class.
         */
        fun bind(sound: Sound) {
            binding.apply {
                viewModel?.sound = sound
                executePendingBindings()
            }
        }
    }

    /**
     * RecyclerView Adapter for the RecyclerView in activity_main.xml
     * @param sounds: A List() containing Sound.kt objects
     */
    private inner class SoundAdapter(private val sounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundHolder {
            val binding = DataBindingUtil.inflate<ListItemSoundBinding>(
                layoutInflater,
                R.layout.list_item_sound,
                parent,
                false
            )
            //region WHEN USING LIVEDATA, SET THE OBSERVER
            // binding.lifecycleOwner = this@MainActivity
            //endregion
            return SoundHolder(binding)
        }

        override fun getItemCount() = sounds.size

        override fun onBindViewHolder(holder: SoundHolder, position: Int) {
            val sound = sounds[position]
            holder.bind(sound)
        }

    }
}
