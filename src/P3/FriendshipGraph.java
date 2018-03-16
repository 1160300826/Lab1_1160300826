package P3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class FriendshipGraph {
	// 邻接表中表对应的链表的顶点
	private class ENode {
		int ivex; // 该边所指向的顶点的位置
		ENode nextEdge; // 指向下一条弧的指针
	}

	// 邻接表中表的顶点
	private class VNode {
		String name; // 顶点信息
		ENode firstEdge; // 指向第一条依附该顶点的弧
	};

	private List<VNode> Graphy = new ArrayList<>();

	/*
	 * 创建图(自己输入数据)
	 */
	public void addVertex(Person friend) {
		VNode newvertex = new VNode();
		newvertex.name = friend.name();
		newvertex.firstEdge = null;
		Graphy.add(newvertex);
	}

	public void addedge(Person friend, Person friend2) {
		// 读取边的起始顶点和结束顶点
		/*
		 * System.out.printf("edge(%d):", i); char c1 = readChar(); char c2 =
		 * readChar();
		 */
		int p1 = getPosition(friend);
		int p2 = getPosition(friend2);
		// 初始化node1
		ENode node1 = new ENode();
		node1.ivex = p2;
		// 将node1链接到"p1所在链表的末尾"
		if (Graphy.get(p1).firstEdge == null)
			Graphy.get(p1).firstEdge = node1;
		else
			linkLast(Graphy.get(p1).firstEdge, node1);

	}

	/*
	 * 将node节点链接到list的最后
	 */
	private void linkLast(ENode list, ENode node) {
		ENode p = list;

		while (p.nextEdge != null)
			p = p.nextEdge;
		p.nextEdge = node;
	}

	/*
	 * 返回ch位置
	 */
	private int getPosition(Person friend) {
		for (int i = 0; i < Graphy.size(); i++)
			if (Graphy.get(i).name == friend.name())
				return i;
		return -1;
	}

	/*
	 * 打印矩阵队列图
	 */
	public void print() {
		System.out.printf("List Graph:\n");
		for (int i = 0; i < Graphy.size(); i++) {
			System.out.printf("%d(%s): ", i, Graphy.get(i).name);
			ENode node = Graphy.get(i).firstEdge;
			while (node != null) {
				System.out.printf("%d(%s) ", node.ivex, Graphy.get(node.ivex).name);
				node = node.nextEdge;
			}
			System.out.printf("\n");
		}
	}

	public int getDistance(Person k, Person j) {
		int i = getPosition(k);
		int i2 = getPosition(j);
		return bfs(i, i2);
	}

	public int bfs(int k, int j) // 以vi为出发点时对邻接表表示的图G进行先深搜索
	{
		int i;
		ENode p = new ENode();
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[Graphy.size()];
		visited[k] = true;
		int distance = -1;
		queue.add(k);
		while (!queue.isEmpty()) {
			distance++;
			i = queue.remove();
			if (i == j) {
				return distance;
			}
			p = Graphy.get(i).firstEdge;
			while (p != null) { // 若vi的邻接点 vj (j= p→adjvex)存在,依次搜索
				if (!visited[p.ivex]) { // 若vj未访问过
					visited[p.ivex] = true; // 给vj作访问过标记
					queue.add(p.ivex); // 访问过的vj入队 }
				}
				p = p.nextEdge;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		FriendshipGraph pG = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Rachel");
		Person ben = new Person("ben");
		Person kramer = new Person("kramer");
		pG.addVertex(rachel);
		pG.addVertex(ross);
		pG.addVertex(ben);
		pG.addVertex(kramer);
		pG.addedge(rachel, ross);
		pG.addedge(ross, rachel);
		pG.addedge(ben, ross);
		pG.addedge(ross, ben);
		// 自定义"图"(输入矩阵队列)
		// pG = new ListDG();
		// 采用已有的"图"
		/* pG = new FriendshipGraph(vexs, edges); */
		System.out.println(pG.getDistance(rachel, ross));
		System.out.println(pG.getDistance(rachel, ben));
		System.out.println(pG.getDistance(rachel, rachel));
		System.out.println(pG.getDistance(rachel, kramer));
		pG.print(); // 打印图*
	}

}
