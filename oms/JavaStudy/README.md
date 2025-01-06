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

# Week 3

## What is concurrent programming?

Concurrent programming is a programming paradigm that enables multiple tasks to execute in overlapping time periods (concurrently), rather than sequentially.

### What is thread?

A thread is the smallest unit of execution.

### What is fork-join framework?

The Fork-Join Framework is a parallel programming framework in Java introduced in Java 7 as part of the java.util.concurrent package. It is designed to efficiently execute tasks that can be broken down into smaller, independent subtasks, and then combined to produce the final result.

### What is completableFuture?

A CompletableFuture in Java is a powerful and flexible feature introduced in Java 8 as part of the java.util.concurrent package. It represents a future result of an asynchronous computation and provides a framework to build complex asynchronous workflows.

### Compare ForkJoinPool with traditional threads?

## Comparison Table

| **Feature**            | **ForkJoinPool**                         | **Traditional Threads**               |
|------------------------|------------------------------------------|---------------------------------------|
| **Parallelism**        | Splits tasks recursively into smaller tasks and processes them using a pool of worker threads.| Tasks are distributed manually across threads, requiring explicit synchronization and partitioning.|
| **Work-Stealing**      | Uses work-stealing to balance load dynamically across worker threads.| No built-in mechanism for work-stealing; load balancing must be handled explicitly.|
| **Ease of Use**     | Provides a high-level API to divide and combine tasks recursively.| Requires manual thread creation, task management, and coordination.|
| **Performance**        | More efficient for CPU-bound, fine-grained parallel tasks (e.g., summing large arrays).| May perform worse for fine-grained tasks due to thread overhead.|
| **Scalability**        | Scales well with the number of available processors.| Scalability is limited by thread overhead and management complexity.|
| **Complexity**  | Simplifies task decomposition and result aggregation.| Requires more boilerplate code for similar tasks.|

---

## What is Java cryptography?
Cryptography in Java involves securing information through encoding (encryption) and decoding (decryption) using cryptographic algorithms.

### Key Components

_Encryption_: Converts plaintext into ciphertext using an algorithm and a key.

_Decryption_: Converts ciphertext back into plaintext using the same or a related key.

_Hashing_: Generates a fixed-size hash value from data, often used for integrity checks.

_Digital Signatures_: Provides data authenticity and integrity.

_Key Management_: Involves generating, distributing, and storing cryptographic keys securely.

### Common Cryptographic Algorithms in Java

1. Symmetric Encryption (Same Key):

Algorithms: AES, DES, Triple DES.

Use Case: Encrypting large data efficiently.

2. Asymmetric Encryption (Public and Private Keys):

Algorithms: RSA, Elliptic Curve.

Use Case: Securing key exchanges, digital signatures.

3. Hashing:

Algorithms: MD5, SHA-1, SHA-256, SHA-512.

Use Case: Password storage, data integrity.

4. Key Derivation Functions:

Algorithms: PBKDF2.

Use Case: Deriving secure keys from passwords.

### Importance of Hashing in Security

1. Irreversible Transformation:

Hashing converts data (e.g., passwords) into a fixed-length string of characters.

It is a one-way function, meaning the original data cannot be retrieved from the hash.

2. Password Storage:

Plaintext passwords are a significant security risk.
Storing hashed passwords ensures that even if the database is compromised, the passwords are not directly accessible.

3. Data Integrity:

Hashes are used to verify the integrity of data. Even a small change in the original data results in a completely different hash.

4. Resistance to Attacks:

Combined with techniques like salting (adding random data before hashing), it becomes resistant to rainbow table attacks and brute force attempts.

5. Compliance:

Many security standards and regulations (e.g., GDPR, PCI DSS) require the secure handling of sensitive data, including hashing passwords.
