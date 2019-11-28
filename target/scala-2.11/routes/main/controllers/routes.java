// @GENERATOR:play-routes-compiler
// @SOURCE:/home/jainebudke/SMACK/conf/routes
// @DATE:Wed Nov 27 23:15:46 UTC 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
  }

}
