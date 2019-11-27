// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/Área de Trabalho/example-play-akka-master/conf/routes
// @DATE:Wed Nov 27 18:06:15 BRT 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
  }

}
