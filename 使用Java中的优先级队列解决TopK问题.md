##使用Java优先级队列解决TopK问题
###问题描述
输入n个整数，找出其中最大的K个数，或者找出其中第K大的数。
###解决方法
Java中优先级队列PriorityQueue是基于优先级堆实现的，默认是使用自然顺序排列的，数字默认最小的排列在队列的头部。调用poll()删除队首的元素之后，再向队列中添加元素，会重新构造优先级堆，队首的元素仍然是队列中的最小元素。

1. 找出topK元素

		public static Object[] topN(int[] arr, int n){
        	PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        	for(int i = 0; i < arr.length; i++){
            	if(priorityQueue.size() < n){
                	priorityQueue.add(arr[i]);
            	}else if(arr[i] > priorityQueue.peek()){
                	priorityQueue.poll();
                	priorityQueue.offer(arr[i]);
            	}
        	}
        	Object[] result = priorityQueue.toArray();
        	Arrays.sort(result);
        	return result;
    	}
2. 找到第K大的元素
		
		public static int findNthNum(int[] arr, int n){
        	PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        	for(int i = 0; i < arr.length; i++){
        	    if(priorityQueue.size() < n){
        	        priorityQueue.add(arr[i]);
        	    }else if(arr[i] > priorityQueue.peek()){
        	        priorityQueue.poll();
        	        priorityQueue.offer(arr[i]);
        	    }
        	}

        	return priorityQueue.peek();
    	}

