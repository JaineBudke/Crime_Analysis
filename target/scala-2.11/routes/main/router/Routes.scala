// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/Área de Trabalho/example-play-akka-master/conf/routes
// @DATE:Thu Nov 28 09:56:07 BRT 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController
  ) = this(errorHandler, HomeController_0, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """loadData""", """controllers.HomeController.loadData()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """classifier/""" + "$" + """cor<.+>/""" + "$" + """turno<.+>/""" + "$" + """sexo<.+>""", """controllers.HomeController.classifier(cor:String, turno:String, sexo:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_loadData1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("loadData")))
  )
  private[this] lazy val controllers_HomeController_loadData1_invoker = createInvoker(
    HomeController_0.loadData(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "loadData",
      Nil,
      "GET",
      this.prefix + """loadData""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_classifier2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("classifier/"), DynamicPart("cor", """.+""",false), StaticPart("/"), DynamicPart("turno", """.+""",false), StaticPart("/"), DynamicPart("sexo", """.+""",false)))
  )
  private[this] lazy val controllers_HomeController_classifier2_invoker = createInvoker(
    HomeController_0.classifier(fakeValue[String], fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "classifier",
      Seq(classOf[String], classOf[String], classOf[String]),
      "GET",
      this.prefix + """classifier/""" + "$" + """cor<.+>/""" + "$" + """turno<.+>/""" + "$" + """sexo<.+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index())
      }
  
    // @LINE:8
    case controllers_HomeController_loadData1_route(params@_) =>
      call { 
        controllers_HomeController_loadData1_invoker.call(HomeController_0.loadData())
      }
  
    // @LINE:10
    case controllers_HomeController_classifier2_route(params@_) =>
      call(params.fromPath[String]("cor", None), params.fromPath[String]("turno", None), params.fromPath[String]("sexo", None)) { (cor, turno, sexo) =>
        controllers_HomeController_classifier2_invoker.call(HomeController_0.classifier(cor, turno, sexo))
      }
  }
}
