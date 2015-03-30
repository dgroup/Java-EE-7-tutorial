package home.dgroup.servlet.db

import javax.persistence.{Entity, GeneratedValue, Id, NamedQuery}
import javax.validation.constraints.{NotNull, Size}

import scala.beans.BeanProperty

/**
 * @author dgroup
 * @since 01.03.2015
 */
@Entity
@NamedQuery(name = "findCommentById", query = "select c from Comment c where c.id = :id")
class Comment {

  @Id
  @GeneratedValue
  @BeanProperty
  var id: Long = 0


  @BeanProperty
  @NotNull
  var author: String = ""

  @BeanProperty
  @NotNull
  @Size(max = 100)
  var email: String = ""

  @BeanProperty
  @NotNull
  @Size(max = 2000)
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