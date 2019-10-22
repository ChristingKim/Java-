#Java LinkedList的源码解析

##LinkedList类的定义
LinkedList 继承 AbstractSequentialList，实现 List、Deque、Cloneable、Serializable。其中 AbstractSequentialList 提供了 List 接口的主要实现。Deque 一个线性 collection，支持在两端插入和移除元素，定义了双端队列的操作。
    
	public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, java.io.Serializable

##LinkedList的节点对象
    
    private static class Node<E> {
        E item;     //当前节点的值
        Node<E> next;   //下一个节点
        Node<E> prev;   //前一个节点

        //构造方法
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
##属性
    transient int size = 0;		//双向链表的大小
	transient Node<E> first;	//双向链表的第一个节点
	transient Node<E> last;		//双向链表的最后一个节点
##构造方法
    //空的双向链表对象
	public LinkedList() {
    }
	
	//使用一个集合对象，声明一个双向链表。
	//集合对象的值会被取出添加到双向链表中，添加的顺序就是集合对象的迭代顺序
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
##方法
+ isElementIndex(int index)
        
		//判断index是否是双向链表中某一节点的索引。双向链表中已存在节点的索引值范围是[0,size)
		private boolean isElementIndex(int index) {
        	return index >= 0 && index < size;
    	}
+ isPositionIndex(int index)
        
		//判断index对于添加和迭代操作，是否是一个有效值。可以在[0,size]范围内，进行添加或者迭代操作
		private boolean isPositionIndex(int index) {
        	return index >= 0 && index <= size;
    	}
+ node(int index)
        
		//返回索引index处的节点。
		//1，判断index的值是否大于size/2;
		//2，若小于，则正序遍历；
		//3，若大于，则倒序遍历；
		Node<E> node(int index) {
        	// assert isElementIndex(index);

        	if (index < (size >> 1)) {
        	    Node<E> x = first;
        	    for (int i = 0; i < index; i++)
        	        x = x.next;
        	    return x;
        	} else {
        	    Node<E> x = last;
        	    for (int i = size - 1; i > index; i--)
        	        x = x.prev;
        	    return x;
        	}
    	}	
+ indexOf(Object o)
        
		//返回对象o在双向链表中的索引
		//根据o是否为null,使用不同的方法比较。
		public int indexOf(Object o) {
        	int index = 0;
        	if (o == null) {
            	for (Node<E> x = first; x != null; x = x.next) {
            	    if (x.item == null)
            	        return index;
            	    index++;
            	}
        	} else {
            	for (Node<E> x = first; x != null; x = x.next) {
            	    if (o.equals(x.item))
            	        return index;
            	    index++;
            	}
        	}
        	return -1;
    	}
+ lastIndexOf(Object o)

        //双向链表中，最后出现对象o的索引
		public int lastIndexOf(Object o) {
       		int index = size;
        	if (o == null) {
            	for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            	}
        	} else {
        	    for (Node<E> x = last; x != null; x = x.prev) {
        	        index--;
        	        if (o.equals(x.item))
        	            return index;
        	    }
        	}
        	return -1;
    	}
+ linkFirst(E e)
        
		//将对象以节点的方式，插入到双向链表的头部（第一个节点）
		//若原第一个节点为null，则代表原双向链表为空。最后一个节点，应该也是新加入的节点。
		private void linkFirst(E e) {
        	final Node<E> f = first;
        	final Node<E> newNode = new Node<>(null, e, f);
        	first = newNode;
        	if (f == null)
            	last = newNode;
        	else
            	f.prev = newNode;
        	size++;
        	modCount++;
    	}
+ void linkLast(E e)
		
		//将对象以节点的方式，插入到双向链表的尾部（最后一个节点）
		//若原最后一个节点为null，则代表原双向链表为空。第一个节点，应该也是新加入的节点
		void linkLast(E e) {
        	final Node<E> l = last;
        	final Node<E> newNode = new Node<>(l, e, null);
        	last = newNode;
        	if (l == null)
            	first = newNode;
        	else
        	    l.next = newNode;
        	size++;
        	modCount++;
    	}	
+ linkBefore(E e, Node<E> succ)
        
		//将对象以节点的形式，插入到节点succ之前。
		//若succ的前节点为空，则succ为第一个节点。新插入的节点变为第一个节点
		void linkBefore(E e, Node<E> succ) {
        	// assert succ != null;
        	final Node<E> pred = succ.prev;
        	final Node<E> newNode = new Node<>(pred, e, succ);
        	succ.prev = newNode;
        	if (pred == null)
            	first = newNode;
        	else
            	pred.next = newNode;
        	size++;
        	modCount++;
    	}