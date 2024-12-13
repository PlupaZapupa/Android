package ru.me.a30fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass.
 * Use the [MessageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MessageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_message, container, false)
        val messageEditText = view.findViewById<EditText>(R.id.messagefragment_edit)

        val translateButton = view.findViewById<Button>(R.id.messagefragment_translate_button)
        translateButton.setOnClickListener {
            val message = messageEditText.text.toString()
            val action = MessageFragmentDirections
                .actionMessageFragmentToConverterFragment(message)
            view.findNavController().navigate(action)
            //view.findNavController().navigate(R.id.action_messageFragment_to_converterFragment)
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment()
    }
}