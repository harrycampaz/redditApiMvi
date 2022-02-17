package com.harrycampaz.redditapi.presentation.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.harrycampaz.redditapi.R
import com.harrycampaz.redditapi.databinding.DetailsFragmentBinding
import com.harrycampaz.redditapi.presentation.home.view.viewholder.loadImg

class DetailsFragment : DialogFragment() {

    private lateinit var viewModel: DetailsViewModel
    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding !!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivthumbnailDetails.loadImg(args.dataPostsVO.thumbnail)
        binding.tvTitleDetails.text = args.dataPostsVO.author_fullname
        binding.tvDescriptionDetails.text = args.dataPostsVO.title
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}