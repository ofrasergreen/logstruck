package com.logstruck

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.{ Context => MacroContext }

object Data {
  def apply(args: Any*): Map[String, Any] = macro DataImpl.apply

  class DataImpl(val c: MacroContext) {
    import c.universe._
    val q"$prefix.${_}[..${_}](..$callerArgs)" = c.macroApplication

    def apply(args: c.Expr[Any]*): c.Expr[Map[String, Any]] = {
      c.Expr(dataMap(args))
    }

    def dataMap(args: Seq[c.Expr[Any]]) = {
      val argsList = args.flatMap { arg =>
        arg.tree match {
          case Ident(sym) =>
            val ident = Literal(Constant(sym.toString))
            List(q"($ident, $arg)")
          case Select(_, TermName(sym)) =>
            List(q"(${Literal(Constant(sym.toString))}, $arg)")
          case Apply(TypeApply(Select(Apply(TypeApply(Select(_,
          TermName("ArrowAssoc")), List(TypeTree())), List(Literal(ident))),
          TermName("$minus$greater")), List(TypeTree())), List(arg)) =>
            List(q"($ident, $arg)")
          case a =>
            println("Failing on: " + showRaw(a))
            c.abort(c.enclosingPosition, "log arguments must be either identifiers or arrow associations")
            List()
        }
      }
      q"Map(..$argsList)"
    }

    def fatal(title: c.Expr[String], args: c.Expr[Any]*): c.Expr[Unit] = log(LogLevel.Fatal, title, args)
    def error(title: c.Expr[String], args: c.Expr[Any]*): c.Expr[Unit] = log(LogLevel.Error, title, args)
    def warn(title: c.Expr[String], args: c.Expr[Any]*): c.Expr[Unit] = log(LogLevel.Warn, title, args)
    def info(title: c.Expr[String], args: c.Expr[Any]*): c.Expr[Unit] = log(LogLevel.Info, title, args)
    def debug(title: c.Expr[String], args: c.Expr[Any]*): c.Expr[Unit] = log(LogLevel.Debug, title, args)
    def trace(title: c.Expr[String], args: c.Expr[Any]*): c.Expr[Unit] = log(LogLevel.Trace, title, args)

    def log(logLevel: LogLevel, title: c.Expr[String], args: Seq[c.Expr[Any]]): c.Expr[Unit] = {

      //      println("Enclosing: " + c.internal.)
      val file = Literal(Constant(c.enclosingPosition.source.file.name))
      val lineNumber = Literal(Constant(c.enclosingPosition.line))
      val source = Literal(Constant(c.internal.enclosingOwner.fullName))
      val level = Select(Select(Select(Ident(TermName("com")), TermName("logstruck")), TermName("LogLevel")), TermName(logLevel.toString))
      val data = dataMap(args)
      val expr = q"""$prefix.log($level, com.logstruck.SourceContext($file, $lineNumber, $source, Map.empty), $title, $data)"""
      //println("Expression: " + expr)
      //println("Raw: " + showRaw(expr))
      c.Expr[Unit](expr)
    }

    def child(args: c.Expr[Any]*): c.Expr[Log] = {
      val data = dataMap(args)
      c.Expr(q"""$prefix.child($data)""")
    }
  }
}
