##Java堆排序
堆是一种完全二叉树，最大堆要求结点元素的值大于或者等于左右两个子结点元素的值。处于最大堆的根结点的元素一定是这个堆中的最大值。在一个实现最大堆的长度为length的数组arrays中，根结点永远处于arrays[0]处。此时将arrays[0]与arrays[length - 1]的元素互换，这样最大值就处在数组的最后面，然后对arrays[0]~arrays[length-2]之间的元素构建最大堆，将arrays[0]与arrays[length - 2]互换，依次操作，最后可得到一个升序的数组。
###构建最大堆
利用迭代依次比较，某一结点与其左右子结点的值，取左右子结点中的最大值与结点交换，最终会得到最大堆。代码如下：
	
		    public static void heapify(int[] arrays, int currentRootIndex, int currentSize){
		        if(currentRootIndex < currentSize){
		            int left = 2*currentRootIndex + 1;
		            int right = 2*currentRootIndex + 2;
		            int maxIndex = currentRootIndex;
		
		            if(left < currentSize){
		                if(arrays[maxIndex] < arrays[left]){
		                    maxIndex = left;
		                }
		            }
		            if(right < currentSize){
		                if(arrays[maxIndex] < arrays[right]){
		                    maxIndex = right;
		                }
		            }
		            if(maxIndex !=  currentRootIndex){
		                int tmp = arrays[maxIndex];
		                arrays[maxIndex] = arrays[currentRootIndex];
		                arrays[currentRootIndex] = tmp;
		
		                heapify(arrays, maxIndex, currentSize);
		            }
		        }
		    }
遍历数组，依次构建最大堆，代码如下：

		    public static void maxHeapify(int[] arrays, int currentSize){
		        for(int i = currentSize - 1; i >= 0; i--){
		            heapify(arrays, i, currentSize);
		        }
		    }

###堆排序代码

		    public static void heapifySort(int[] arrays){
		        for(int i = 0; i < arrays.length; i++){
		            maxHeapify(arrays, arrays.length-i);
		            int tmp = arrays[0];
		            arrays[0] = arrays[arrays.length - 1 - i];
		            arrays[arrays.length - 1 - i] = tmp;
		        }
		    }