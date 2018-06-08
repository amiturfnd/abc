package controllers

import javax.inject.Inject
import play.api.db._
import play.api.mvc._

class UserController @Inject() (db: Database, val controllerComponents: ControllerComponents) extends BaseController {

  def index = Action {
    var outString = "Number is "
    val conn = db.getConnection()

    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("SELECT 9 as testkey ")

      while (rs.next()) {
        outString += rs.getString("testkey")
      }
    } finally {
      conn.close()
    }
    Ok(outString)
  }

  def index2 = Action { implicit request =>
    Ok {
      request.flash.get("success").getOrElse("Welcome!")
    }
  }


  def hello(name: String) = Action {
    Ok(views.html.hello(name))
  }
}