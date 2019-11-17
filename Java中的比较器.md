#Java中Comparator和Comparable的使用
##1 简介
Java中的比较功能是非常容易实现的。在Java中轻松地通过Comparator和Comparable接口，制定比较策略。
##2 实例
此处使用一个足球运动员的Player对象作为例子介绍Comparator和Comparable的使用。Player中有三个变量：ranking、name、age。如下所示：
<pre>public class Player{
    private int ranking;
    private String name;
    private int age;
}</pre>
创建三个Player对象，并将它们放入到List列表中，然后使用*Clollections.sort()*方法对其排序。代码如下：
<pre>
    public static void main(String[] args) {
        List<Player> footballTeam = new ArrayList<>();
        Player player1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 22);
        Player player3 = new Player(45, "Steven", 24);
        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);

        System.out.println("Before Sorting : " + Arrays.toString(footballTeam.toArray()));
        Collections.sort(footballTeam);
        System.out.println("After Sorting : " + Arrays.toString(footballTeam.toArray()));

    }
</pre>
此时运行代码，会提示一个运行时错误，如下：
<pre>
Error:(18, 20) java: 对于sort(java.util.List<Player>), 找不到合适的方法
    方法 java.util.Collections.<T>sort(java.util.List<T>)不适用
      (推论变量 T 具有不兼容的限制范围
        等式约束条件: Player
        上限: java.lang.Comparable<? super T>)
    方法 java.util.Collections.<T>sort(java.util.List<T>,java.util.Comparator<? super T>)不适用
      (无法推断类型变量 T
        (实际参数列表和形式参数列表长度不同))
</pre>
提示List<T>中的对象没有实现Comparable接口。
##4 Comparable
*Comparable*是一个定义对象之间比较规则的接口。为了能使用*Collections.sort()*方法对*Player*对象实现排序，*Player*对象需要实现*Comparable*接口：
<pre>
public class Player implements Comparable<Player>{
	//...
    @Override
    public int compareTo(Player o) {
        return this.getRanking() - o.getRanking();
    }
}
</pre>
*compareTo()*方法返回的结果代表，两个对象之间的关系：大于、小于、等于。最后，我们再次运行排序程序，的输出如下：
<pre>
Before Sorting : [John, Roger, Steven]
After Sorting : [Steven, John, Roger]
</pre>
接下来，介绍一下*Comparator*接口的用法。
##5 Comparator
*Comparator*接口定义了方法*compare(arg1, arg2)*，两个参数代表要比较的两个对象，类似于*Comparable.compareTo()*方法。
###5.1 实现Comparator接口
我们创建一个*Comparator*对象使用*ranking*字段去给*Player*列表排序。
<pre>
public class PlayerRankingComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.getRanking() - o2.getRanking();
    }
}
</pre>
同样，我们也可以创建一个*Comparator*对象使用*age*字段去为*Player*列表排序。
<pre>
public class PlayerAgeComparator implements Comparator<Player> {
    @Override
    public int compare(Player o1, Player o2) {
        return o1.getAge() - o2.getAge();
    }
}
</pre>
###5.2 Comparator对象的使用
*Java.util*包中的*Collections*对象中的方法介绍如下：
<pre>
public static <T> void sort(List<T> list, Comparator<? super T> c)
根据指定的比较器引起的顺序对指定的列表进行排序。 列表中的所有元素必须使用指定的比较器相互比较 
</pre>
使用PlayerRankingComparator进行排序，程序如下：
<pre>
    PlayerRankingComparator playerComparator = new PlayerRankingComparator();
    Collections.sort(footballTeam, playerComparator);
</pre>
输出结果如下：
<pre>
Before Sorting : [John, Roger, Steven]
After Sorting : [Steven, John, Roger]
</pre>
使用PlayerAgeComparator进行排序，程序如下：
<pre>
    PlayerAgeComparator playerComparator = new PlayerAgeComparator();
    Collections.sort(footballTeam, playerComparator);
</pre>
输出结果如下：
<pre>
Before Sorting : [John, Roger, Steven]
After Sorting : [John, Roger, Steven]
</pre>