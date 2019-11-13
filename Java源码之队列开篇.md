##Java源码之队列
队列(queue)是一种特殊的线性表，只允许在一端（前端front）进行删除操作，而在另一端（后端rear）进行插入操作，进行插入操作的端称为队尾，进行删除操作的端称为队头。队列中没有元素时，称为空队列。最早进入队列的元素才能最先从队列中删除，所以队列又称为先进先出（FIFO-first in first out）线性表。

队列分为三种：

1. 单向队列（Queue）：只能在一端插入数据，另一端删除数据。
2. 双向队列（Deque）：每一端都可以进行插入数据和删除数据操作。
3. 优先级队列（PriorityQueue）：保存队列元素的顺序不是按照其加入队列的顺序，而是按照队列元素的大小进行重新排序。 

###单向队列Queue接口
Java中队列的接口Queue是一个被设计用来按一定的优先级处理元素的集合，除了拥有基本的集合操作和数据接口队列的特性之外，它还额外添加了元素的添加、删除、获取的操作。这些操作又分为两种形式，第一种如果方法调用失败就会抛出异常，第二种方法如果调用失败不会抛出异常，而是返回一个指定的值，通常是null或者false。

####Queue接口的声明
		
Queue是一个集合，具有基本的集合操作功能。


	public interface Queue<E> extends Collection<E> {
		......
	}


####Queue中的方法

	boolean add(E e);   //将指定的元素添加入队列，如果队列是有界的且没有空闲空间则抛出异常
	boolean offer(E e);		//将指定的元素添加入队列，如果队列是有界的且没有空闲控件则返回false。在使用有界队列时推荐使用该方法来替代add方法。
	E remove();		//remove删除并返回队首的元素，如果队列为空则抛出异常。
	E poll();		//poll删除并返回队首的元素。如果队列为空返回null。
	E element();	//element返回队首的元素但是不删除。如果队列为空则会抛出异常。
	E peek();		//peek返回队首元素但是不删除，如果队列为空则返回null.

###双向队列Deque接口
 Java中Deque用来表示双向队列。双向队列是一种在队列的两端都可以进行插入和删除等操作的队列。Deque是一个比Stack和Queue功能更强大的接口，它同时实现了栈和队列的功能。Deque既可以用作后进先出的栈，也可以用作先进后出的队列。
####Deque接口的声明
Deque继承自Queue，声明如下：

	public interface Deque<E> extends Queue<E> {
		......
	}
####Deque接口中的方法

	void addFirst(E e);		//将指定的元素添加到队列的首部，如果队列是有界的且没有空闲空间则抛出异常。
	void addLast(E e);		//将指定的元素添加到队列的尾部，如果队列是有界的且没有空闲空间则抛出异常。
	boolean offerFirst(E e);	//将指定的元素添加到队列的首部，如果队列是有界的且没有空闲空间则返回false。
	boolean offerLast(E e);		//将指定的元素添加到队列的尾部，如果队列是有界的且没有空闲空间则返回false。
	E removeFirst();	//删除队列首部的元素，如果队列为空则抛出异常。
	E removeLast();		//删除队列尾部的元素，如果队列为空则抛出异常。
	E pollFirst();		//删除队列首部的元素，如果队列为空则返回null。
	E pollLast();		//删除队列尾部的元素，如果队列为空则返回null。
	E getFirst();		//返回队列首部元素的值，如果队列为空则抛出异常。
	E getLast();		//返回队列尾部元素的值，如果队列为空则抛出异常。
	E peekFirst();		//返回队列首部元素的值，如果队列为空则返回null。
	E peekLast();		//返回队列尾部元素的值，如果队列为空则返回null。
	boolean removeFirstOccurrence(Object o);	//删除队列中第一次出现的某一元素。
	boolean removeLastOccurrence(Object o);		//删除队列中最后一次出现的某一元素。
	boolean add(E e);   //将指定的元素添加入队列，如果队列是有界的且没有空闲空间则抛出异常
	boolean offer(E e);		//将指定的元素添加入队列，如果队列是有界的且没有空闲控件则返回false。在使用有界队列时推荐使用该方法来替代add方法。
	E remove();		//remove删除并返回队首的元素，如果队列为空则抛出异常。
	E poll();		//poll删除并返回队首的元素。如果队列为空返回null。
	E element();	//element返回队首的元素但是不删除。如果队列为空则会抛出异常。
	E peek();		//peek返回队首元素但是不删除，如果队列为空则返回null。
	void push(E e);		//将指定元素添加到队列的首部，如果没有空闲控件则抛出异常。
	E pop();	//弹出队列头部的元素，如果队列为空则抛出异常。
	boolean remove(Object o);		//删除队列中的元素。
	boolean contains(Object o);		//队列中是否包含某元素。
	public int size();		//队列的大小。
	
push()和pop()方法实现了栈的功能。
