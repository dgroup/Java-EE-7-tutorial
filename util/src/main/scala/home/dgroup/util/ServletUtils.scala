package home.dgroup.util

import javax.servlet.RequestDispatcher
import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpSession, Part}

import org.apache.commons.lang3.Validate._

/**
 * @author dgroup on 14.03.2015.
 */
object ServletUtils {

  def getParameterAsString(req: HttpServletRequest, parameterName: String): String = {
    notNull(req, "Request can't be a null")
    notNull(parameterName, "Key can't be a null")
    val par: AnyRef = req.getParameter(parameterName)
    if (par == null) return ""
    par.toString
  }

  def addToSession(req: HttpServletRequest, key: String, value: AnyRef) {
    addToSession(req, key, value, create = true)
  }

  def addToSession(req: HttpServletRequest, key: String, value: AnyRef, create: Boolean) {
    notNull(req, "Request can't be a null")
    add(key, value, req.getSession(create))
  }

  def add(key: String, value: AnyRef, session: HttpSession) {
    notNull(key, "Key can't be a null")
    notNull(value, "Value can't be a null")
    notNull(session, "Session can't be a null")
    session.setAttribute(key, value)
  }

  def forward(url: String, req: HttpServletRequest, resp: HttpServletResponse) {
    assertUrl(url)
    forward(req.getRequestDispatcher(url), req, resp)
  }

  def forward(dispatcher: RequestDispatcher, req: HttpServletRequest, resp: HttpServletResponse) {
    notNull(dispatcher, "Dispatcher can't be a null")
    try {
      dispatcher.forward(req, resp)
    }
    catch {
      case e: Any => throw new ForwardException(e)
    }
  }

  def include(url: String, req: HttpServletRequest, resp: HttpServletResponse) {
    assertUrl(url)
    forward(req.getRequestDispatcher(url), req, resp)
  }

  def include(dispatcher: RequestDispatcher, req: HttpServletRequest, resp: HttpServletResponse) {
    notNull(dispatcher, "Dispatcher can't be a null")
    try {
      dispatcher.include(req, resp)
    }
    catch {
      case e: Any => throw new IncludeException(e)
    }
  }

  private def assertUrl(url: String) {
    assertString(url, "URL can't be a blank")
  }

  private def assertString(text: String, message: String) {
    notBlank(text, message)
  }

  def copyAttachment(request: HttpServletRequest) {
    copyFile(request, "attachment")
  }

  /**
   * TODO not implemented yet
   * @param parameter it's a name in form
   **/
  def copyFile(request: HttpServletRequest, parameter: String) {
    notNull(request, "Request can't be a null")
    assertString(parameter, "Parameter can't be a null")
    try
      request.getParts

    catch {
      case e: Any => throw new CopyFileException(e)
    }
  }

  private def getFileName(part: Part): String = {
    val headerContent: String = part.getHeader("content-disposition")
    for (token <- headerContent.split(";")) {
      if (token.trim.startsWith("filename")) {
        return token.substring(token.indexOf('=') + 2, token.length - 1)
      }
    }
    ""
  }
}

final class ServletUtils {
}
