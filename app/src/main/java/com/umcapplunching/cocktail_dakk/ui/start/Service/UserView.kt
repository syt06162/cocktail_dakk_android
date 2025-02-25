package com.umcapplunching.cocktail_dakk.ui.start.Service

interface SignupView {
    fun onSignupLoading()
    fun onSignupSuccess(userbody: Userbody)
    fun onSignupFailure(code:Int, message:String)
}

interface AutoLoginView {
    fun onLoginLoading()
    fun onLoginSuccess(autologinbody: Autologinbody)
    fun onLoginFailure(code:Int, message:String)
}

interface iSFavorokView {
    fun onFavorLoading()
    fun onFavorSuccess(isfavorok: Isfavorok)
    fun onFavorFailure(code:Int, message:String)
}

interface getUserInfoView {
    fun onGetUinfoLoading()
    fun onGetUinfoSuccess(userinfo: Userinfo)
    fun onGetUinfoFailure(code:Int, message:String)
}

interface TokenSigninView {
    fun onTokenSigninLoading()
    fun onTokenSigninSuccess(tokenSigninbody: Tokenrespbody)
    fun onTokenSigninFailure(code:Int, message:String)
}

interface TokenResfreshView {
    fun onTokenRefreshLoading()
    fun onTokenRefreshSuccess(tokenSigninbody: Tokenrespbody)
    fun onTokenRefreshFailure(code:Int, message:String)
}



