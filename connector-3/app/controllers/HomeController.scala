package controllers


import akka.actor.TypedActor.dispatcher
import model.TodoListItem

import scala.collection.mutable
//import com.sun.tools.internal.ws.processor.model.Response

import javax.inject._
import play.api._
import play.api.libs.ws._
import play.api.mvc._
import play.mvc.Results.status
import play.api.libs.json._

import scala.concurrent.Future
import scala.concurrent.duration.DurationInt
import play.api.libs.concurrent.Futures._
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(ws: WSClient,
                                val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  private val todoList = new mutable.ListBuffer[TodoListItem]()
  todoList += TodoListItem(1, "test", true)
  todoList += TodoListItem(2, "some other value", false)
  implicit val todoListJson = Json.format[TodoListItem]


  def getAll(): Action[AnyContent] = Action {
    /*if (todoList.isEmpty) {
      NoContent
    } else {
      Ok(Json.toJson(todoList))
    }*/
    val response = requests.get("https://www.fishwatch.gov/api/species")
    Ok(response.toString())
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    val response = requests.get("https://www.fishwatch.gov/api/species")
    Ok(response.toString())
  }


}
