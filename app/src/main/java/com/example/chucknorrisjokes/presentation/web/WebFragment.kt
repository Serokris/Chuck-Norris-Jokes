package com.example.chucknorrisjokes.presentation.web

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.chucknorrisjokes.databinding.FragmentWebBinding
import com.example.chucknorrisjokes.presentation.MainActivity
import com.example.chucknorrisjokes.common.Constants.BASE_URL
import com.example.chucknorrisjokes.presentation.base.BaseBindingFragment

class WebFragment : BaseBindingFragment<FragmentWebBinding>(FragmentWebBinding::inflate) {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.builtInZoomControls = true
            loadUrl(BASE_URL)
        }

        val webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(BASE_URL)

                if (url.contains("api.chucknorris.io")) {
                    return false
                }

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                activity.startActivity(intent)
                return true
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        binding.webView.webViewClient = webViewClient
    }
}