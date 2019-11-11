##ArrayList源码阅读总结
###与LinkedList的区别

ArrayList是基于动态数组实现的数据结构。LinkedList是基于链表(指针)实现的数据结构。对于随机访问get和set，ArrayList要优于LinkedList，因为LinkedList要移动指针。对于添加和删除操作add和remove，LinkedList要优于ArrayList，因为ArrayList要使用数组拷贝数据，而LinkedList只需要改变指针的指向即可实现。

###ArrayList中数组管理
ArrayList是基于数组实现的，当添加、删除数据时，要检测管理数组的大小。
####ArrayList中数组的大小

在向ArrayList中添加元素时，检查数组的大小。数组的初始大小为10。当数组不能容纳新元素时，就将原来的容量扩大0.5倍。
'''

    //minCapacity：数组的最小容量。添加新元素时，需要的最少容量。
	private void ensureCapacityInternal(int minCapacity) {

		//如果数组为空，则最小容量就是minCapacity
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

'''


    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

		//如果minCapacity大于数组的长度，则进行扩容操作
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

'''

    //数组扩容操作
	private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);	//容量增加1/2
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);

        // 创建一个新的数组，长度为newCapacity，并将elementData中的元素拷贝进去
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

####ArrayList中数组的操作

###ArrayList构造函数


	    public ArrayList(int initialCapacity) {
	        if (initialCapacity > 0) {
	            this.elementData = new Object[initialCapacity];
	        } else if (initialCapacity == 0) {
	            this.elementData = EMPTY_ELEMENTDATA;
	        } else {
	            throw new IllegalArgumentException("Illegal Capacity: "+
	                                               initialCapacity);
	        }
	    }

	    public ArrayList() {
	        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	    }

	    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

###ArrayList访问

根据索引index获取元素，代码如下：

    E elementData(int index) {
        return (E) elementData[index];
    }

    public E get(int index) {
        rangeCheck(index); 	//检查索引是否有效，如果不生效，则抛出异常

        return elementData(index);
    }

###设置索引index处的元素值

    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

###添加元素

    public boolean add(E e) {
        ensureCapacityInternal(size + 1); 
        elementData[size++] = e;
        return true;
    }

###在索引index处添加元素

    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1); 
        System.arraycopy(elementData, index, elementData, index + 1,
                         size - index);
        elementData[index] = element;
        size++;
    }
###删除索引index处的元素

     public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

###添加集合类中的元素
     public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

###从索引index处添加集合中的元素

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                             numMoved);

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

###删除索引区间[fromIndex, toIndex]内的元素

    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                         numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

###保留集合之间的交集

    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return batchRemove(c, true);
    }

    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                System.arraycopy(elementData, r,
                                 elementData, w,
                                 size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }