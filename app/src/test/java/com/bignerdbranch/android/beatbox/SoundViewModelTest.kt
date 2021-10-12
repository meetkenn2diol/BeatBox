package com.bignerdbranch.android.beatbox

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**A Test class for SoundViewModel.kt*/
class SoundViewModelTest {
    private lateinit var beatBox: BeatBox
    private lateinit var sound: Sound
    private lateinit var subject: SoundViewModel

    /**
     * With the @Before annotation, this method serve as a location for initializing properties.
     * This unit test is to only test the functionality fo SoundViewModel class.
     *
     * The constructor mock(Class) in org.mockito.Mockito [e.g mock(BeatBox::class.java)] will make sure that beatbox object
     * will work even if there are some runtime errors on some othe function in BeatBox class
     */
    @Before
    fun setUp() {
        beatBox = mock(BeatBox::class.java)
        sound = Sound("assetPath")
        subject = SoundViewModel(beatBox)
        subject.sound = sound
    }

    /**
     * SoundViewModel: The title property is connected to the Sound’s name property.
     * This function tests to make sure it's the same.
     */
    @Test
    fun exposesSoundNameAsTitle() {
        assertThat(subject.title, `is`(sound.name))
    }

    /**
     * This function tests the onclick listener of the subject
     */
    @Test
    fun callsBeatBoxPlayOnButtonClicked() {
        subject.onButtonClicked()
        /** “Verify that the play(…) function was called on beatBox with this specific
        sound as a parameter.”*/
        verify(beatBox).play(sound)
    }
}