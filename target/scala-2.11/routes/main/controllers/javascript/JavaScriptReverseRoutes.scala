// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/Área de Trabalho/example-play-akka-master/conf/routes
// @DATE:Thu Nov 28 09:56:07 BRT 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:10
    def classifier: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.classifier",
      """
        function(cor0,turno1,sexo2) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "classifier/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("cor", cor0) + "/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("turno", turno1) + "/" + (""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("sexo", sexo2)})
        }
      """
    )
  
    // @LINE:8
    def loadData: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.loadData",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "loadData"})
        }
      """
    )
  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }


}
