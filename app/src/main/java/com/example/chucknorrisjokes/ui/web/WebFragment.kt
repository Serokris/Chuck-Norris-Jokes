package com.example.chucknorrisjokes.ui.web

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.chucknorrisjokes.databinding.FragmentWebBinding
import com.example.chucknorrisjokes.ui.MainActivity

private const val URL_API_SERVICE = "https://api.chucknorris.io/"

class WebFragment : Fragment() {

    private lateinit var binding: FragmentWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        binding = FragmentWebBinding.inflate(inflater)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.loadUrl(URL_API_SERVICE)

        val webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(URL_API_SERVICE)

                if (url.contains("api.chucknorris.io")) {
                    return false
                }

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                activity!!.startActivity(intent)
                return true
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        binding.webView.webViewClient = webViewClient

        return binding.root
    }
}