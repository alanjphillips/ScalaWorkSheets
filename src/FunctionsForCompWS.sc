import scala.concurrent.Future
import scala.util.{Failure, Random, Success}

val even: Int => Boolean = {
  x => x % 2 == 0
}

even eq even

even(3)

val same: (Int, Int) => Boolean = {
  (x, y) => x == y
}

val s1 = same(1, 2)

val s2 = same(1, 2)

s1.equals(s2)


val numbers = 1 to 10

def double(n: Int): Int = {
  n * 2
}

val doubleF = double _

val multiply: (Int, Int) => Int = {
  (x, y) => x * y
}

val mult = (a: Int, b: Int) => a * b

val multiply3 = multiply(_: Int, 3)

numbers map println

val tripleNumbers = numbers.map(
  multiply3(_)
)

tripleNumbers map println

val r = new Random
val rNumber = () => r.nextInt()

def callByName(x: => Int): List[Int] = {
  List(x, x)
}

callByName(rNumber())
callByName(r.nextInt())

import scala.concurrent.ExecutionContext.Implicits.global

val someFut: Future[String] = Future { "some" }
val thingFut: Future[String] = Future { "thing" }
val moreFut: Future[String] = Future { "more" }

val c: Future[String] = for {
  s1 <- someFut
  s2 <- thingFut
  s3 <- moreFut
} yield s1 + s2 + s3

val c2: Future[String] = someFut.flatMap(
  s1 => thingFut.flatMap(
    s2 => moreFut.map(
      s3 => s1 + s2 + s3
    )
  )
)

c2.onComplete {
  case Success(x) => println(x)
  case Failure(e) => e.printStackTrace
}

val fut1: Future[Int] = Future { 1 }
val fut2: Future[Int] = Future { 2 }
val fut3: Future[Int] = Future { 3 }

val futStr1: Future[String] = fut1.map(
  i => i.toString
)

val fResultForComp: Future[Int] = for {
  f1 <- fut1
  f2 <- fut2
  f3 <- fut3
} yield f1 + f2 + f3

val fResult: Future[Int] = fut1.flatMap(
  f1 => fut2.flatMap(
    f2 => fut3.map(
      f3 => f1 + f2 + f3
    )
  )
)

val toFutStr: (Int) => Future[String] = i => Future { i.toString }
val futStr2: Future[String] = fut1.flatMap(toFutStr)