# Memory Types (Stack & Heap)

## Stack

Each Java Virtual Machine thread has a private Java Virtual Machine stack, created at the same time as the thread. A Java Virtual Machine stack stores frames (§2.6). A Java Virtual Machine stack is analogous to the stack of a conventional language such as C: it holds local variables and partial results, and plays a part in method invocation and return. Because the Java Virtual Machine stack is never manipulated directly except to push and pop frames, frames may be heap allocated. The memory for a Java Virtual Machine stack does not need to be contiguous.

In the First Edition of The Java® Virtual Machine Specification, the Java Virtual Machine stack was known as the Java stack.

This specification permits Java Virtual Machine stacks either to be of a fixed size or to dynamically expand and contract as required by the computation. If the Java Virtual Machine stacks are of a fixed size, the size of each Java Virtual Machine stack may be chosen independently when that stack is created.

A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of Java Virtual Machine stacks, as well as, in the case of dynamically expanding or contracting Java Virtual Machine stacks, control over the maximum and minimum sizes.

_**The following exceptional conditions are associated with Java Virtual Machine stacks:**_

If the computation in a thread requires a larger Java Virtual Machine stack than is permitted, the Java Virtual Machine throws a StackOverflowError.

If Java Virtual Machine stacks can be dynamically expanded, and expansion is attempted but insufficient memory can be made available to effect the expansion, or if insufficient memory can be made available to create the initial Java Virtual Machine stack for a new thread, the Java Virtual Machine throws an OutOfMemoryError.

ref: https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.5.2
## Heap

The Java Virtual Machine has a heap that is shared among all Java Virtual Machine threads. The heap is the run-time data area from which memory for all class instances and arrays is allocated.

The heap is created on virtual machine start-up. Heap storage for objects is reclaimed by an automatic storage management system (known as a garbage collector); objects are never explicitly deallocated. The Java Virtual Machine assumes no particular type of automatic storage management system, and the storage management technique may be chosen according to the implementor's system requirements. The heap may be of a fixed size or may be expanded as required by the computation and may be contracted if a larger heap becomes unnecessary. The memory for the heap does not need to be contiguous.

A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of the heap, as well as, if the heap can be dynamically expanded or contracted, control over the maximum and minimum heap size.

_**The following exceptional condition is associated with the heap:**_

If a computation requires more heap than can be made available by the automatic storage management system, the Java Virtual Machine throws an OutOfMemoryError.

ref: https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.5.3

# Data Types & Immutable Types

## Data Types

Like the Java programming language, the Java Virtual Machine operates on two kinds of types: primitive types and reference types. There are, correspondingly, two kinds of values that can be stored in variables, passed as arguments, returned by methods, and operated upon: primitive values and reference values.

More details:

Primitive Types and Values (https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.3)

Reference Types and Values (https://docs.oracle.com/javase/specs/jvms/se23/html/jvms-2.html#jvms-2.4)

## Immutable Types

Immutable types are types of data objects whose state cannot be changed after they are created.

Read more about Immutability

refs:

Hard Core book
https://learning.oreilly.com/library/view/hardcore-java/0596005687/ch03.html

A Functional Approach book
https://learning.oreilly.com/library/view/a-functional-approach/9781098109912/ch04.html