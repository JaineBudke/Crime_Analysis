// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/Área de Trabalho/example-play-akka-master/conf/routes
// @DATE:Wed Nov 27 18:06:15 BRT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
