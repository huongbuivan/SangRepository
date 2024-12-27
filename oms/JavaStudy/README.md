# Week 1
## What is functional interface?
Any interface with a SAM(Single Abstract Method) is a functional interface.

ref: https://www.baeldung.com/java-8-functional-interfaces

## What is memory leak?

A Memory Leak is a situation where there are objects present in the heap that are no longer used, but the garbage collector is unable to remove them from memory, and therefore, they’re unnecessarily maintained.

ref: https://www.baeldung.com/java-memory-leaks

# Week 2
## Memory Types (Stack & Heap)

### What is stack?

Each Java Virtual Machine thread has a private Java Virtual Machine stack, created at the same time as the thread. A Java Virtual Machine stack stores frames.

ref: https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.5.2

### What is heap?

The Java Virtual Machine has a heap that is shared among all Java Virtual Machine threads. The heap is the run-time data area from which memory for all class instances and arrays is allocated.

The heap is created on virtual machine start-up. Heap storage for objects is reclaimed by an automatic storage management system (known as a garbage collector).

ref: https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.5.3

## Data Types & Immutable Types

### What is data Types?

The Java Virtual Machine operates on two kinds of types: primitive types and reference types. There are, correspondingly, two kinds of values that can be stored in variables, passed as arguments, returned by methods, and operated upon: primitive values and reference values.

More details:

Primitive Types and Values (https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.3)

Reference Types and Values (https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.4)

### What is immutable types?

Immutable types are types of data objects whose state cannot be changed after they are created.

Read more about Immutability

refs:

Hard Core book
https://learning.oreilly.com/library/view/hardcore-java/0596005687/ch03.html

A Functional Approach book
https://learning.oreilly.com/library/view/a-functional-approach/9781098109912/ch04.html
