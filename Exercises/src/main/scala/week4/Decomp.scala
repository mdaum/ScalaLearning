package week4

trait Expr {
  def eval: Int = this match {
    case Number(n: Int) => n
    case Sum(e1: Expr, e2: Expr) =>
      e1.eval + e2.eval
  }
  def show: String = this match {
    case Number(n: Int) => s"${n}"
    case Sum(e1, e2) => s"${e1.show} + ${e2.show}"
  }
}
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Number(n: Int) extends Expr
