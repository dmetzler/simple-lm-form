package binders

import play.api.mvc.PathBindable
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.text.ParseException

object `package` {

  implicit def dateTimePathBindable(implicit stringBinder: PathBindable[String]) = new PathBindable[DateTime] {

    def bind(key: String, value: String): Either[String, DateTime] =
      try {
        for {
          date <- Some(new DateTime(new SimpleDateFormat("yyyy-MM-dd").parse(value))).toRight("Parse error").right
        } yield date
      } catch {
        case e: ParseException => Left("Cannot parse parameter " + key + " as DateTime: " + e.getMessage)
      }

    def unbind(key: String, value: DateTime): String = {
      stringBinder.unbind(key, value.toString("yyyy-mm-dd"))
    }
  }
}