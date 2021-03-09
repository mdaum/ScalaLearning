package week5;

object section5_1 {
  // note that :: means add elem to end, but ::: means concat the lists
  def removeAt(n: Int, xs: List[Int]): List[Int] = (xs.take(n)) ::: (xs.drop(n + 1))

  def main(args: Array[String]): Unit = {
    val fruit = List("apples", "oranges", "pears")
    val nums = List(1, 2, 3)
    val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
    val empty = List()
  }
}
