#Lru算法
##原理
Lru，即最近最少使用算法Least Recently Used。在缓存管理中，Lru将最近没有使用的数据从缓存中移除。

如果一个数据在最近一段时间内没有被访问，那么在将来它被访问的可能性也很小。当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。
##实现思路
一般基于双向链表实现LRU算法，双向链表的大小固定。涉及到的操作如下：

+ 缓存数据
	
	缓存数据，即向双向链表中添加数据。新数据会被添加到双向链表的头部。双向链表的容量为capacity，大小为count。
	
	当count+1 > capacity时，双向链表的尾部数据，会被移除；

	当count+1 <= capacity时，新数据会被添加到双向链表的头部； 

+ 访问数据 

	被访问的数据，会被移到双向链表的头部；

##实现代码

###LRU类

    import java.util.HashMap;

	public class Lru {

	    private int count;
	    private int capacity;
	    private Node first = null;
	    private Node last = null;
	
	    private HashMap<String, Node> cache = new HashMap<String, Node>();
	
	    public Lru(int capacity){
	        this.count = 0;
	        this.capacity = capacity;
	    }
	
	    //访问某一个值
	    public int get(String key){
	        Node node = this.cache.get(key);
	        if(node == null){
	            return -1;
	        }
	
	        this.removeNode(node);
	        this.addNode(node);
	        return node.value;
	    }
	
	    //缓存中添加某值
	    public void set(String key, int value){
	        Node node = cache.get("key");
	
	        if(node == null){
	
	            Node newNode = new Node();
	            newNode.key = key;
	            newNode.value = value;
	            this.cache.put(key, newNode);
	            this.addNode(newNode);

	            count++;
	
	            if(count > capacity){
	                Node tail = this.popLast();
	                this.cache.remove(tail.key);
	                count--;
	            }
	        }else{
	            node.value = value;
	            this.moveToHead(node);
	        }
	    }

	    //新增节点，添加到双向链表的头部
	    private void addNode(Node node){

	        final Node f = first;
	        node.next = f;
	        if(f == null){
	            last = node;
	        }else{
	            f.prev = node;
	        }

	        node.next = f;
	        node.prev = null;
	        first = node;
	    }

	    //删除双向链表中的节点
	    private void removeNode(Node node){
	        Node prev = node.prev;
	        Node next = node.next;
	
	        if(prev == null){
	            next.prev = null;
	            first = next;
	        }else{
	            prev.next = node.next;
	        }

	        if(next == null){
	            prev.next = null;
	            last = prev;
	        }else{
	            next.prev = prev;
	        }
  	  }

    	//节点移到双向链表的头部
    	private void moveToHead(Node node){
        	this.removeNode(node);
        	this.addNode(node);
   	 	}

    	//移除尾部的节点
    	private Node popLast(){
       	 	Node tail = last;
        	this.removeNode(tail);
        	return tail;
    	}

    	@Override
    	public String toString(){
        	StringBuffer strBuf = new StringBuffer();
        	Node node = first;
        	while(node != null){
            	strBuf.append(String.format("%s:%s ", node.key, node.value));
            	node = node.next;
        	}
        	return strBuf.toString();
    	}

    	class Node{
        	Node prev;
        	Node next;
        	String key;
        	Integer value;
    	}
	}

###测试代码
        static void lruCache(){
        	System.out.println();
        	Lru lruCache = new Lru(5);
        	lruCache.set("1", 11);
        	lruCache.set("2", 12);
        	lruCache.set("3", 13);
        	lruCache.set("4", 14);
        	lruCache.set("5", 15);
        	System.out.println(lruCache.toString());

        	lruCache.set("6", 66);
        	lruCache.get("2");
        	lruCache.set("7", 77);
        	lruCache.get("4");
        	System.out.println(lruCache.toString());
        	System.out.println();

    	}

###输出结果
    5:15 4:14 3:13 2:12 1:11 
	4:14 7:77 2:12 6:66 5:15 



	