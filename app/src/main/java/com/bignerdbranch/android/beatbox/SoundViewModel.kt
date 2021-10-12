package com.bignerdbranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

/**
 * Make the view model communicate with the layout file when a change occurs.To do this, your view model needs to implement data binding’s Observable interface. This interface lets your binding class set listeners on your view model so that it can automatically receive callbacks when its fields are modified.
 *
 * Three steps are required:
 *1. Subclass BaseObservable in your view model.
 *2. Annotate your view model’s bindable properties with @Bindable.
 *3. Call notifyChange() or notifyPropertyChanged(Int) each time a bindable property’s value changes.
 *
 * @param beatBox: a BeatBox object for playing Sound
 * @see   com.bignerdbranch.android.beatbox.BeatBox
 */
class SoundViewModel(private val beatBox: BeatBox) : BaseObservable() {

    var sound: Sound? = null
        set(sound) {
            field = sound
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name

    /** A function to call when an AppCompatButton connected to this SoundViewModel is clicked*/
    fun onButtonClicked() {
        sound?.let {
            beatBox.play(it)
        }
    }

}

/**
class SoundViewModel {
val title: MutableLiveData<String?> = MutableLiveData()

var sound: Sound? = null
set(sound) {
field = sound
title.postValue(sound?.name)
}
 */