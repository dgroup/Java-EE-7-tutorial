package home.dgroup.servlet.db

import scala.beans.BeanProperty

/**
 * @author dgroup
 * @since 01.03.2015
 */
class Comment {

  @BeanProperty
  var author: String = ""

  @BeanProperty
  var email: String = ""

  @BeanProperty
  var text: String = ""

  def this(author: String, email: String, text: String) {
    this()
    setAuthor(author)
    setEmail(email)
    setText(text)
  }

  override def toString: String = {
    "Comment{" + "author='" + author + "', email='" + email + "', text='" + text + "'}"
  }

}