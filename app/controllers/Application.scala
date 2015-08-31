package controllers

import models.{DB, Person}
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._


object Application extends Application {

}

class Application extends Controller {

  def index = Action {
    val persons = DB.query[Person].fetch().toList
    Ok(views.html.index("Hello,World", persons: List[models.Person]))
  }

  val personForm: Form[Person] = Form {
    mapping(
      "name" -> text
    )(Person.apply)(Person.unapply)
  }

  def addPerson() = Action {
    implicit Request =>
      val person: Person = personForm.bindFromRequest.get
      DB.save(person)
      Redirect(routes.Application.index())
  }

  def getPersons() = Action {
    var persons = DB.query[Person].fetch()
    Ok(Json.toJson(persons))
  }
}
