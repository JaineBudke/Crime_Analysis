// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/SMACK/conf/routes
// @DATE:Thu Nov 28 13:49:12 UTC 2019


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
