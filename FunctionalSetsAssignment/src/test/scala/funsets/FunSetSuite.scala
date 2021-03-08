package funsets

import org.junit._

/**
  * This class is a test suite for the methods in object FunSets.
  *
  * To run this test suite, start "sbt" then run the "test" command.
  */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
    * When writing tests, one would often like to re-use certain values for multiple
    * tests. For instance, we would like to create an Int-set and have multiple test
    * about it.
    *
    * Instead of copy-pasting the code for creating the set into every test, we can
    * store it in the test class using a val:
    *
    *   val s1 = singletonSet(1)
    *
    * However, what happens if the method "singletonSet" has a bug and crashes? Then
    * the test methods are not even executed, because creating an instance of the
    * test class fails!
    *
    * Therefore, we put the shared values into a separate trait (traits are like
    * abstract classes), and create an instance inside each test method.
    *
    */
  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val all = union(union(union(union(union(s5, s6), s4), s3), s2), s1)
  }

  /**
    * This test is currently disabled (by using @Ignore) because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", remvoe the
    * @Ignore annotation.
    */
  @Test def `singleton set one contains one`: Unit = {

    /**
      * We create a new instance of the "TestSets" trait, this gives us access
      * to the values "s1" to "s3".
      */
    new TestSets {

      /**
        * The string argument of "assert" is a message that is printed in case
        * the test fails. This helps identifying which assertion failed.
        */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersect contains intersection of the sets`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      val r = union(s2, s3)
      val intersection = intersect(s, r)
      assert(!contains(intersection, 1), "Intersect 1")
      assert(contains(intersection, 2), "Intersect 2")
      assert(!contains(intersection, 3), "Intersect 3")
    }
  }

  @Test def `diff contains diff of the sets`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      val r = union(s2, s3)
      val difference = diff(s, r)
      assert(contains(difference, 1), "Diff 1")
      assert(!contains(difference, 2), "Diff 2")
      assert(!contains(difference, 3), "Diff 3")
    }
  }

  @Test def `can filter out all odd numbers in set, and check if odd exists`: Unit = {
    new TestSets {
      val evens = filter(all, x => if (x % 2 == 0) true else false)
      assert(!contains(evens, 1), "filter 1")
      assert(contains(evens, 2), "filter 2")
      assert(!contains(evens, 3), "filter 3")
      assert(contains(evens, 4), "filter 4")
      assert(!contains(evens, 5), "filter 5")
      assert(contains(evens, 6), "filter 6")
      assert(!exists(evens, x => if (x % 2 != 0) true else false))
    }
  }

  @Test def `can map a set with function x*x`: Unit = {
    new TestSets {
      val squared = map(all, x => x * x)
      assert(contains(squared, 1), "map 1")
      assert(contains(squared, 4), "map 2")
      assert(contains(squared, 9), "map 3")
      assert(contains(squared, 16), "map 4")
      assert(contains(squared, 25), "map 5")
      assert(contains(squared, 36), "map 6")
    }
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
