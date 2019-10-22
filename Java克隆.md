#Java的克隆方法
##克隆的概念
Java使用"="运算符拷贝一个对象的时候，只是将等号右边的引用赋值给等号左边的引用，二者指向的还是同一块内存，所以任何一个引用对内存的操作都直接反映到另一个引用上。</br>
克隆的意思就是在堆中克隆出一块和原对象一样的对象，并将这个对象的地址赋予新的引用，从而使对新引用的操作，不会影响到原引用。</br>
对于所有的基本数据类型，"="运算符都会对其原值进行拷贝，因为基本数据类型，不是对象，不是存储在堆中。
##Cloneable接口和Object的clone()方法
Cloneable是一个没有任何方法的接口，在Java中只有实现了Cloneable接口的对象，才能使用clone()方法拷贝对象。
###Cloneable接口的使用方法
1. 类事项Cloneable接口，以指示Object的clone()方法可以合法地对该类实例进行按字段复制；
2. 如果在没有实现Cloneable接口的实例上调用Object的clone()方法，会导致CloneNotSupportedException异常；
3. 按照惯例，实现Cloneable接口的类应该使用公共方法重写Object的clone()方法，Object的clone()方法是一种protected方法；
###实例代码
类CloneObject的代码</br>


	public class CloneObject implements Cloneable{

	    private String name;

    	public CloneObject(){

    	}

    	public String getName() {
    	    return name;
    	}

    	public void setName(String name) {
    	    this.name = name;
    	}

    	public CloneObject clone() throws CloneNotSupportedException
    	{
    	    return (CloneObject)super.clone();
    	}
	}

克隆方法使用代码

    
	    try{
            CloneObject cloneObj1 = new CloneObject();
            cloneObj1.setName("Test");

            CloneObject cloneObj2 = cloneObj1;
            cloneObj2.setName("Text");

            Boolean equalResult = cloneObj1 != cloneObj2;

            System.out.println("cloneObj1 != cloneObj2 :" +  equalResult);
            System.out.println("cloneObj1.getName() :" + cloneObj1.getName());
            System.out.println("cloneObj2.getName() :" + cloneObj2.getName());

            CloneObject cloneObj3 = cloneObj1.clone();
            cloneObj3.setName("Text_Test");

            Boolean equalResult1 = cloneObj1 != cloneObj3;

            System.out.println("cloneObj1 != cloneObj3 :" +  equalResult1);
            System.out.println("cloneObj1.getName() :" + cloneObj1.getName());
            System.out.println("cloneObj3.getName() :" + cloneObj3.getName());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

输出结果

    cloneObj1 != cloneObj2 :false
	cloneObj1.getName() :Text
	cloneObj2.getName() :Text
	cloneObj1 != cloneObj3 :true
	cloneObj1.getName() :Text
	cloneObj3.getName() :Text_Test
##深克隆和浅克隆
浅克隆（shallow clone）和深克隆（deep clone）反映的是，当对象中还有对象的时候，那么：

1、浅克隆，即很表层的克隆，如果我们要克隆对象，只克隆它自身以及它所包含的所有对象的引用地址

2、深克隆，克隆除自身对象以外的所有对象，包括自身所包含的所有对象实例