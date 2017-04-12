1. Provide your own implementation of the linked list in functional style using Scala and then implement functions to:
```
transform your list [1,2,3,4,5] to [2,3,4,5,6];

transform your list ["a","b","c","d"] to ["a","a","b","b","c","c"];

transform your list [1,2,3,4,5] to [5,4,3,2,1];

combine your lists [1,2,3,4,5] and [5,4,3,2,1] to [(1,5),(2,4),(3,3),(4,2),(5,1)].
```

2. Implement the function which splits a string and keeps separators (in functional style using Scala).
Example:
```
def split(by:String*)(of:String):List[String] = ???
split(by="+","-")(of="3.8 - 49 + 7") == List("3.8 ","-"," 49 ","+"," 7")
```

3. Write a program which makes a GET request to some URL and prints a response to console (in functional style in Scala).
  You can use scalaz/cats etc.

4. Optional. Implement a recursive map function using scalaz’s Trampoline. Replace ??? with your code:
```
import scalaz.Free.Trampoline
import scalaz.Trampoline
def map[A,B](l:List[A],f:A=>B):Trampoline[List[B]] = l match {
  case Nil => Trampoline.done(???)
  case x :: xs => Trampoline.suspend(???).flatMap(x => ???)
}
map[Int,Int]((1 to 300000).toList, x => x + 1).run
```