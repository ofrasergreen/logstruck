package com.logstruck

import com.logstruck.Filter.{Rule, Predicate}

import scala.annotation.tailrec

class Filter(rules: List[Rule]) {
  def test(event: Event): Boolean = testR(event, rules)

  @tailrec
  private final def testR(event: Event, rules: List[Rule]): Boolean = {
    rules match {
      case Nil => true
      case r :: ules =>
        r.evalute(event) match {
          case Some(Accept) => true
          case Some(Drop) => false
          case Some(filter) => filter.test(event)
          case None => testR(event, ules)
        }
    }
  }
}

object Filter {
  def apply(rules: Rule*) = new Filter(rules.toList)
  type Predicate = (Event => Boolean)

  class Rule(predicate: Predicate, target: Filter) {
    def evalute(event: Event): Option[Filter] = if (predicate(event)) Some(target) else None
  }

  class RuleBuilder(p: Predicate) {
    def ->(f: Filter): Rule = new Rule(p, f)
  }

  implicit def predicateBuilder(p: Predicate): RuleBuilder = new RuleBuilder(p)
  object Rule {
    val x = Map(1 -> 2)
  }
}

object Accept extends Filter(Nil)

object Drop extends Filter(Nil)