package pe.senati

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.senati.databinding.FragmentCommerceBinding
import java.text.Bidi


class CommerceFragment : Fragment() {

    private lateinit var mBiding: FragmentCommerceBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBiding=FragmentCommerceBinding.inflate(inflater,container,false)

        // Inflate the layout for this fragment
      return mBiding.root

    }


}