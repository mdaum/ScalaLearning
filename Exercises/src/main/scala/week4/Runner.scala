package week4
object Runner {
  def testList(): Unit = {
    println("*****LIST TEST START*****")
    val empty = List()
    val size1 = List(1)
    println(s"${size1.head}")
    val l = List(1, 2)
    println(s"${l.head} => ${l.tail.head}")
    println("*****LIST TEST END*****")
  }
  def testDecomp(): Unit = {
    println("*****LIST DECOMP START*****")
    println(Sum(Number(1), Number(44)).show)
    println("*****LIST DECOMP END*****")
  }

  def main(args: Array[String]): Unit = {
    testList()
    testDecomp()
  }
}
