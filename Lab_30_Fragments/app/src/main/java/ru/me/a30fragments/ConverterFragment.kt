package ru.me.a30fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Use the [ConverterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConverterFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_converter, container, false)

        val message = ConverterFragmentArgs.fromBundle(requireArguments()).message
        var meow = ""
        repeat(message.length){
            meow += "мяу "
        }
        val translatedText = view.findViewById<TextView>(R.id.converterfragment_text)
        translatedText.text = meow

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConverterFragment()
    }
}