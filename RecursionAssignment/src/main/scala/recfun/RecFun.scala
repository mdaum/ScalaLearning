package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def abs(x: Int) = if (x >= 0) x else x * -1
    def factorial(x: Int): Int =
      if (x==0) 1 else x * factorial(x-1)
    factorial(r)/(factorial(c) * factorial(abs(r-c)))
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def balanceImpl(chars: List[Char], depth: Int): Boolean = {
      if(depth < 0) false else
        if (chars.isEmpty) {
          if (depth == 0) true else false
        } else {
          val head = chars.head
          val tail = chars.tail
          if(head == '(') balanceImpl(tail, depth + 1) else
            if(head == ')') balanceImpl(tail, depth - 1)
            else balanceImpl(tail, depth)
        }
    }
    balanceImpl(chars, 0)
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) 1 // successfully made change
    else if(money < 0 || coins.isEmpty) 0 // did not successfully make change
    else countChange(money - coins.head, coins) + countChange(money, coins.tail) // try all the ways possible
  }
}
