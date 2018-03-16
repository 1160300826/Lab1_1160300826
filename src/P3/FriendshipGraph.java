package P3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class FriendshipGraph {
	// �ڽӱ��б��Ӧ������Ķ���
	private class ENode {
		int ivex; // �ñ���ָ��Ķ����λ��
		ENode nextEdge; // ָ����һ������ָ��
	}

	// �ڽӱ��б�Ķ���
	private class VNode {
		String name; // ������Ϣ
		ENode firstEdge; // ָ���һ�������ö���Ļ�
	};

	private List<VNode> Graphy = new ArrayList<>();

	/*
	 * ����ͼ(�Լ���������)
	 */
	public void addVertex(Person friend) {
		VNode newvertex = new VNode();
		newvertex.name = friend.name();
		newvertex.firstEdge = null;
		Graphy.add(newvertex);
	}

	public void addedge(Person friend, Person friend2) {
		// ��ȡ�ߵ���ʼ����ͽ�������
		/*
		 * System.out.printf("edge(%d):", i); char c1 = readChar(); char c2 =
		 * readChar();
		 */
		int p1 = getPosition(friend);
		int p2 = getPosition(friend2);
		// ��ʼ��node1
		ENode node1 = new ENode();
		node1.ivex = p2;
		// ��node1���ӵ�"p1���������ĩβ"
		if (Graphy.get(p1).firstEdge == null)
			Graphy.get(p1).firstEdge = node1;
		else
			linkLast(Graphy.get(p1).firstEdge, node1);

	}

	/*
	 * ��node�ڵ����ӵ�list�����
	 */
	private void linkLast(ENode list, ENode node) {
		ENode p = list;

		while (p.nextEdge != null)
			p = p.nextEdge;
		p.nextEdge = node;
	}

	/*
	 * ����chλ��
	 */
	private int getPosition(Person friend) {
		for (int i = 0; i < Graphy.size(); i++)
			if (Graphy.get(i).name == friend.name())
				return i;
		return -1;
	}

	/*
	 * ��ӡ�������ͼ
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

	public int bfs(int k, int j) // ��viΪ������ʱ���ڽӱ��ʾ��ͼG������������
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
			while (p != null) { // ��vi���ڽӵ� vj (j= p��adjvex)����,��������
				if (!visited[p.ivex]) { // ��vjδ���ʹ�
					visited[p.ivex] = true; // ��vj�����ʹ����
					queue.add(p.ivex); // ���ʹ���vj��� }
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
		// �Զ���"ͼ"(����������)
		// pG = new ListDG();
		// �������е�"ͼ"
		/* pG = new FriendshipGraph(vexs, edges); */
		System.out.println(pG.getDistance(rachel, ross));
		System.out.println(pG.getDistance(rachel, ben));
		System.out.println(pG.getDistance(rachel, rachel));
		System.out.println(pG.getDistance(rachel, kramer));
		pG.print(); // ��ӡͼ*
	}

}
