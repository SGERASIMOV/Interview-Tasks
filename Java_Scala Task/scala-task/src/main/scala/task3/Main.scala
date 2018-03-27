package task3

object Main extends App {

  val stack = Cons(1L, Empty)
  println(stack)
  val stack1 = stack.push(2).push(3)
  println(stack1)
  println(s"head:${stack1.head}")
  println(stack1.tail)
  println(Empty)
}