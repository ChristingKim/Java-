##Java 数组的复制
1. java System.arrayCopy

	java.lang.System.arraycopy()方法复制指定的原数组中的数据。在指定的位置开始，到目标数组的指定位置。
		
		public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		Object src：原数组
		int srcPos：从原数组的起始位置开始
		Object dest：目标数组
		int destPos：目标数组的开始起始位置
		int lenght：要copy的数组长度
2. Arrays.copyof
	
	Arrays.copyOf方法复制指定的数组，截取或用默认值填充，以使副本具有指定的长度。返回的副本是新数组。

		Arrays.copyOf(T[] original, int newLength)
			T[] original：原始数组，U可以为基本数据类型，或者对象
			int newLength：数组的长度。当newLength大于原数组的长度时，超出的部分以默认值填充。当newLength小于原数组的长度时，截取有效的长度。

		copyOf(U[] original, int newLength, Class<? extends T[]> newType)
			T[] original：原始数组，U可以为基本数据类型，或者对象
			int newLength：数组的长度。当newLength大于原数组的长度时，超出的部分以默认值填充。当newLength小于原数组的长度时，截取有效的长度
			T[] newType：数组中存储对象的泛型

3. 	Arrays.copyOfRange
	
	拷贝原数组中指定区间内的元素，返回一个新的数组
	
		copyOfRange(T[] original, int from, int to)
		T[] original：原数组
		int from：起始索引
		int to：终止索引

		copyOfRange(U[] original, int from, int to, Class<? extends T[]> newType)
		T[] original：原数组
		int from：起始索引
		int to：终止索引
		T[] newType：数组中存储元素的泛型
	