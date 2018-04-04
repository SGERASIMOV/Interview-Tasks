package task3

import org.scalatest.{FreeSpec, Matchers}

class TSStackTest extends FreeSpec with Matchers {

  class EmptyStackFixture {
    val stack: TSStack[Int] = Empty
  }

  class StackWithOneElementFixture {
    val stack: TSStack[Int] = TSStack(1)
  }

  "check stack isEmpty operation" in new EmptyStackFixture {
    assert(stack.isEmpty)
  }

  "should produce NoSuchElementException when head is invoked" in new EmptyStackFixture {
    intercept[NoSuchElementException](stack.head)
  }

  "should produce NoSuchElementException when tail is invoked" in new EmptyStackFixture {
    intercept[NoSuchElementException](stack.tail)
  }

  "should produce NoSuchElementException after push, tail and tail are invoked" in new EmptyStackFixture {
    intercept[NoSuchElementException](stack.push(2).tail.tail)
  }

  "toString on Empty stack: TSStack()" in new EmptyStackFixture {
    assert(stack.toString == "TSStack()")
  }

  "check isEmpty operation on non-Empty stack" in new StackWithOneElementFixture {
    assert(!stack.isEmpty)
  }

  "check push-head operations on non-Empty stack" in new StackWithOneElementFixture {
    assert(stack.push(3).head == 3)
    assert(stack.push(4).head == 4)
  }

  "check tail operation on non-Empty stack" in new StackWithOneElementFixture {
    assert(stack.push(2).tail == TSStack(1))
  }

  "toString with 1 element" in new StackWithOneElementFixture {
    assert(stack.toString == "TSStack(1)")
  }

  "toString with 2 elements" in new StackWithOneElementFixture {
    assert(stack.push(2).toString == "TSStack(2, 1)")
  }

  "push then pop" in new EmptyStackFixture {
    assert(stack.push(1).pop == Empty)
  }

  "push, push then pop" in new EmptyStackFixture {
    assert(stack.push(1).push(2).pop == TSStack(1))
  }

  "push and then pop, pop " in new EmptyStackFixture {
    intercept[NoSuchElementException](stack.push(1).pop.pop)
  }

  "pop on Empty stack" in new EmptyStackFixture {
    intercept[NoSuchElementException](stack.pop)
  }

}
