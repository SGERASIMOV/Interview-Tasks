package task3

import scala.annotation.tailrec

sealed abstract class TSStack[+A] {
  def isEmpty: Boolean

  def head: A

  def tail: TSStack[A]

  def push[B >: A](x: B): TSStack[B] = {
    new Cons[B](x, this)
  }

  def pop: TSStack[A] = {
    if (!isEmpty) this.tail
    else throw new NoSuchElementException("pop of empty stack")
  }
}

case object Empty extends TSStack[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException("head of empty stack")

  override def tail: TSStack[Nothing] = throw new NoSuchElementException("tail of empty stack")

  override def toString: String = {
    "TSStack()"
  }
}

case class Cons[+T](hd: T, tl: TSStack[T]) extends TSStack[T] {
  override def isEmpty: Boolean = false

  override def head: T = hd

  override def tail: TSStack[T] = tl

  override def toString: String = {
    @tailrec
    def loop(sb: StringBuilder, stack: TSStack[T]): String = stack match {
      case Empty => sb.toString()
      case st: Cons[T] =>
        sb.append(st.head)
        if (!st.tail.isEmpty) sb.append(", ")
        loop(sb, st.tail)
    }

    s"TSStack(${loop(StringBuilder.newBuilder, this)})"
  }
}

object TSStack {
  def apply[A](seq: A*): TSStack[A] = {
    @tailrec
    def loop(list: List[A], acc: TSStack[A]): TSStack[A] = list match {
      case Nil => acc
      case x :: xs => loop(xs, acc.push(x))
    }

    loop(seq.toList.reverse, Empty)
  }
}
