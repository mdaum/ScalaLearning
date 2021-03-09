package week4

trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

class Nil[T] extends List[T] {
  def isEmpty: Boolean = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  // List(1,2) = List.apply(1,2)
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))
  def apply[T](x: T): List[T] = new Cons[T](x, new Nil)
  def apply[T]() = new Nil
}

object Runner {
  def main(args: Array[String]): Unit = {
    val empty = List()
    val size1 = List(1)
    println(s"${size1.head}")
    val l = List(1, 2)
    println(s"${l.head} => ${l.tail.head}")
  }
}
