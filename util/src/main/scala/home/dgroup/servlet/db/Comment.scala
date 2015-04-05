package home.dgroup.servlet.db

import javax.persistence.{Entity, GeneratedValue, Id, NamedQuery}
import javax.validation.constraints.{Min, NotNull, Size}

import org.hibernate.validator.constraints.Email

import scala.beans.BeanProperty

/**
 * @author dgroup
 * @since 01.03.2015
 */
@Entity
@NamedQuery(name = "findById", query = "select c from Comment c where c.id = :id")
class Comment {

  @Id
  @GeneratedValue
  @Min(value = 1)
  @BeanProperty
  var id: Long = 0

  @BeanProperty
  @NotNull(message="'Author' is a required field")
  @Size(max = 40)
  var author: String = ""

  @BeanProperty
  @NotNull
  @Email(message = "Email has wrong format")
  var email: String = ""

  @BeanProperty
  @NotNull
  @Size(max = 2000, message = "Text should not be more than 2000 symbols")
  var text: String = ""


  def this(id: Long, author: String, email: String, text: String) {
    this()
    setId(id)
    setAuthor(author)
    setEmail(email)
    setText(text)
  }


  def this(author: String, email: String, text: String) {
    this(0, author, email, text)
  }


  override def toString: String = {
    "Comment{id='" + id + "', author='" + author + "', email='" + email + "', text='" + text + "'}"
  }
}