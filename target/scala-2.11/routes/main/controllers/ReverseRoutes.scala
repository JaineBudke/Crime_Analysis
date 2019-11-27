// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/Área de Trabalho/example-play-akka-master/conf/routes
// @DATE:Wed Nov 27 18:06:15 BRT 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def loadData(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "loadData")
    }
  
    // @LINE:6
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
