val partialF1: PartialFunction[Option[Int], Int] = {
  case Some(a) if a <= 100 => a * 2
}

val partialF2: PartialFunction[Option[Int], Int] = {
  case Some(b) if b > 100 => b * 10
}

val partialF3: PartialFunction[Option[Int], Int] = {
  case None => 0
}

val myPartialF4 = partialF1 orElse partialF2 orElse partialF3

myPartialF4(Some(10))

myPartialF4(Some(101))

myPartialF4(None)

partialF1.isDefinedAt(Some(10))

partialF1.isDefinedAt(Some(1000))