package com.example.bohubrihiecommerce.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.bohubrihiecommerce.activities.ProductActivity
import com.example.bohubrihiecommerce.databinding.FragmentProductDetailsBinding
import com.example.bohubrihiecommerce.models.Product
import com.example.bohubrihiecommerce.utils.Constants
import com.example.bohubrihiecommerce.utils.ImageDownloadListener
import com.example.bohubrihiecommerce.utils.ImageDownloader
import com.example.bohubrihiecommerce.utils.Status
import com.example.bohubrihiecommerce.viewmodels.ProductViewModel

class ProductDetailsFragment : Fragment() {

    private lateinit  var binding : FragmentProductDetailsBinding
    private val productViewModel : ProductViewModel by viewModels()
    private var id : String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromBundle()
        getSingleProductDetails()

        binding.purchaseButton.setOnClickListener {
            (requireActivity() as ProductActivity).switchToSuccessFragment()
        }

    }
    private fun getDataFromBundle(){
        id = arguments?.getString(Constants.PRODUCT_ID)

    }

    private fun getSingleProductDetails(){
        id?.let {
            productViewModel.getSingleProduct(it)
            downloadImageAndAttemptLoad()
            observeProductDetails()
        }
    }

    private fun observeProductDetails(){
        productViewModel.getSingleProductData()?.observe(viewLifecycleOwner , Observer {
            if(it.status == Status.SUCCESS){
                binding.loaderConatiner.visibility = View.GONE

                val data = it.data
                data?.let {
                    updateUI(it)
                }
            }else{
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun downloadImageAndAttemptLoad(){
        id?.let {
            ImageDownloader.downloadImage(it , object: ImageDownloadListener{
                override fun onImageDownload(bitmap: Bitmap?) {
                    bitmap?.let {
                        Glide.with(requireContext())
                            .load(it)
                            .into(binding.image)
                    }
                }

            })
        }

    }

    private fun updateUI(product : Product){
        binding.descriptionTextView.text = product.description
        binding.priceSetTextView .text = product.price.toString()
        binding.productNameSetTextView.text = product.name
    }


}