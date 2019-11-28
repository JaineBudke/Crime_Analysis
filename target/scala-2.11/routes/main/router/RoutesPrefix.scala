// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/SMACK/conf/routes
// @DATE:Wed Nov 27 23:15:46 UTC 2019


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
