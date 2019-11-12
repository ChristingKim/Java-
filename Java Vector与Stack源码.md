##Java Vector与Stack源码

###Vector解析
Vector类实现了一个可增长的对象数组。像数组一样，可以通过整型索引来获取内容，但是Vector的大小可以按照实际元素数量的需求进行增长或收缩。</br>	
Vector与ArrayList非常接近，区别在于ArrayList的事项是非同步的，在多线程环境中可能出现线程不安全的情况，而Vector则是线程安全的。Vector类为了保证线程安全性，在每个方法的声明中都使用了synchronized关键字。</br>
在Vector类中，如果构造函数中没有指定每次扩容时应该增加的容量大小，则每次扩容默认为容量加倍。（ArrayList每次扩容是容量默认增加0.5倍）

###Stack解析
Stack类实现的就是我们非常常用的一种数据结构栈。栈是一种后进先出的队列（LIFO）。在JDK的实现中Stack类是Vector类的一个子类。但其额外实现了三个方法：

1. 入栈push(E item)

	向栈中压入一个元素。

2. 出栈pop()

	将栈顶的元素弹出栈。因为栈是使用的数组实现，数组尾部的元素即为栈顶的元素。

3. 获取栈顶元素peek()
	
	返回栈顶的元素值，即数组尾部的最后一个有效元素的值。

###Collections.synchronizedList

Vector、Stack与ArrayList的最大区别就是Vector和Stack是现成安全的。JDK提供了一个方法Collections.synchronizedList()将List类包装成线程安全的。如下代码所示：

	private static List<String> TEST_LIST = Collections.synchronizedList(new ArrayList<String>());
