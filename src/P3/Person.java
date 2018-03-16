package P3;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
	public static List<String> friends = new ArrayList<>();

	public Person(String name) {
		int flag=0;
		this.name = name;
		for(int i=0;i<friends.size();i++)
			if(name==friends.get(i))
				{flag=1;
				System.out.print(friends.get(i));
				}
		if(flag==0)
		friends.add(name);
		else
			{System.out.print("输入人的名字重复！");
			System.exit(-1);}
	}

	public String name() {
		return name;
	}
}
