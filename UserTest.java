import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserTest {
		public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Black：");
		String str_First=sc.next();
		System.out.println("White:");
		String str_Second=sc.next();
		Board b=new Board();
		int flag=b.compareCards(str_First,str_Second);
		if(flag>0) {
			System.out.println("Black win!");
		}else if(flag==0){
			System.out.println("Tie");

		}else{System.out.println("White win");
}
            
	}

}
class Board{
	public String getCardArr(char s) throws Exception {
		String str="";
			switch(s) {
			case 'A':
				str="A";
				break;
			case '2':
				str="2";
				break;
			case '3':
				str="3";
				break;
			case '4':
				str="4";
				break;
			case '5':
				str="5";
				break;
			case '6':
				str="6";
				break;
			case '7':
				str="7";
				break;
			case '8':
				str="8";
				break;
			case '9':
				str="9";
				break;
			case 'T':
				str="T";
				break;
			case 'J':
				str="J";
				break;
			case 'Q':
				str="Q";
				break;
			case 'K':
				str="K";
				break;
			default:
				throw new  Exception("牌面值越界输入异常");
			}
		return str;
	}
	
	public int getCardSign(String cards) throws Exception {
		String s="";
		String s1="";
		//接收花色
		for(int i=1;i<cards.length();i+=2) {
			s1+=cards.charAt(i)+"";
		}
		//接收牌面值
		for(int i=0;i<cards.length();i+=2) {
			s+=cards.charAt(i)+"";
		}
		//判断花色
		for(int i=0;i<s1.length();i++) {
			switch(s1.charAt(i)) {
			case 'S':
				System.out.println("黑桃"+getCardArr(s.charAt(i)));
				break;
			case 'H':
				System.out.println("红桃"+getCardArr(s.charAt(i)));
				break;
			case 'C':
				System.out.println("梅花"+getCardArr(s.charAt(i)));
				break;
			case 'D':
				System.out.println("方块"+getCardArr(s.charAt(i)));
				break;
			default:
				throw new  Exception("花色值越界输入异常");
			}
		}
		System.out.print("该牌为：");
		return getCardslevel(s,s1);
	}
	
	public int getCardslevel(String s,String s1) {
		//count_s用于统计牌面相同值
		int count_s=0;
		//level用于设置等级，默认为0
		int level = 0;
		//遍历字符串
		for(int i=0;i<s.length();i++) {
			for(int j=i+1;j<s.length();j++) {
				//比较值是否相同
				if((s.charAt(i)+"").equals((s.charAt(j)+""))){
					count_s++;
				}
			}
		}
		switch(count_s) {
		case 1://两张相同
			level=1;
			System.out.println("一对");
			break;
		case 2://两对牌相同
			level=2;
			System.out.println("两对");
			break;
		case 3://三张相同
			level=3;
			System.out.println("三张");
			break;
		case 4://3+2
			level=6;
			System.out.println("葫芦");
			break;
		case 6://四张相同
			level=7;
			System.out.println("四条");
			break;
		default:
			level=getCardsColor(s,s1);
		}
		System.out.println("level="+level+"\n");
		return level;
	}
	public int getCardsColor(String s,String s1) {
		//count_s1用于统计花色相同值
		int count_s1=0;
		int level=0;
		//遍历字符串
		for(int i=0;i<s1.length();i++) {
			for(int j=i+1;j<s1.length();j++) {
			//比较值是否相同
				if((s1.charAt(i)+"").equals((s1.charAt(j)+""))){
					count_s1++;
				}
			}
		}
		switch(count_s1) {
		case 10:
			level=5;
			System.out.print("同花");
			if(transition(s)==true) {
				level=8;
				System.out.println("顺");
			}else {
				System.out.println();
			}
			break;
		default:
			if(transition(s)==true) {
				level=4;
				System.out.println("顺子");
			}else {
				System.out.println("杂牌");
			}
		}
		return level;
	}
	public boolean transition(String s) {
		char[] a=s.toCharArray();
		int[] b=new int[a.length];
		for(int j=0;j<a.length;j++) {
			if (Character.isDigit(a[j])){  // 判断是否是数字
			    b[j]= Integer.parseInt(String.valueOf(a[j]));
			} else if(a[j]=='T') {
				b[j]=10;
			}else if(a[j]=='J') {
				b[j]=11;
			}else if(a[j]=='Q') {
				b[j]=12;
			}else if(a[j]=='K') {
				b[j]=13;
			}else if(a[j]=='A') {
				b[j]=1;
			}
		}
		return isNStraightHand(b, b.length);
	}
	public boolean isNStraightHand(int[] b, int length) {
        if (b == null || b.length == 0 || b.length % length != 0) {
            return false;
        }
        // 数组排序
        Arrays.sort(b);
        //并将元素及其出现次数存储在 map 中
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : b) {
        	//将元素存储到map并存储其默认值为1
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int h : b) {
        	//首先判断该键的默认值是否大于0
            if (map.get(h) > 0) {
                for (int j = 0; j < length; j++) {
                	 // 判断 map 中以h为首的连续数字是不是所有元素构成顺子
                    if (map.getOrDefault(h + j, 0) > 0) {
                        map.put(h + j, map.get(h + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
	public int ColorBord(String cardsFirst, String cardsSecond) {
		String s_first="";
		String s1_Second="";
		for(int i=1;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		for(int i=1;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		char[] a=s_first.toCharArray();
		int[] b=new int[a.length];
		System.out.println("Black：");
		for(int j=0;j<a.length;j++) {
			if(a[j]=='S') {
				b[j]=4;
			}else if(a[j]=='H') {
				b[j]=3;
			}else if(a[j]=='C') {
				b[j]=2;
			}else if(a[j]=='D') {
				b[j]=1;
			}
			System.out.print(b[j]);
		}
		System.out.println();
		char[] a1=s1_Second.toCharArray();
		int[] b1=new int[a1.length];
		System.out.println("White：");
		for(int j=0;j<a1.length;j++) {
			if(a1[j]=='S') {
				b1[j]=4;
			}else if(a1[j]=='H') {
				b1[j]=3;
			}else if(a1[j]=='C') {
				b1[j]=2;
			}else if(a1[j]=='D') {
				b1[j]=1;
			}
			System.out.print(b1[j]);
		}
		System.out.println();
		int count=1;
		int count1=1;
		int n=0;
		Arrays.sort(b);
		Arrays.sort(b1);
		for(int i=0;i<b.length-1;i++) {
			if(b[i]==b[i+1]) {
				count++;
				n=i;
			}
		}
		for(int i=0;i<b1.length-1;i++) {
			if(b1[i]==b1[i+1]) {
				count1++;
			}
		}	
//		System.out.println("重复花色数为："+count+"===="+count1);
		if(count<count1) {
			return -1;
		}else if(count==count1) {
			if(b[n]<b1[n]) {
				return -1; 
			}else if(b[n]==b1[n]) {
				System.out.println("两副牌等级相同，花色等级也相同，进行总点数比较：");
				return ValueBord(cardsFirst,cardsSecond);
			}
		}
		return 1;
	}
	public int ValueBord(String cardsFirst,String cardsSecond) {
		String s_first="";
		String s1_Second="";
		for(int i=0;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		for(int i=0;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		char[] a=s_first.toCharArray();
		int[] b=new int[a.length];
		for(int j=0;j<a.length;j++) {
			if (Character.isDigit(a[j])){  // 判断是否是数字
			    b[j]= Integer.parseInt(String.valueOf(a[j]));
			} else if(a[j]=='T') {
				b[j]=10;
			}else if(a[j]=='J') {
				b[j]=11;
			}else if(a[j]=='Q') {
				b[j]=12;
			}else if(a[j]=='K') {
				b[j]=13;
			}else if(a[j]=='A') {
				b[j]=1;
			}
		}
		char[] a1=s1_Second.toCharArray();
		int[] b1=new int[a1.length];
		for(int j=0;j<a1.length;j++) {
			if (Character.isDigit(a1[j])){  // 判断是否是数字
			    b1[j]= Integer.parseInt(String.valueOf(a1[j]));
			} else if(a1[j]=='T') {
				b1[j]=10;
			}else if(a1[j]=='J') {
				b1[j]=11;
			}else if(a1[j]=='Q') {
				b1[j]=12;
			}else if(a1[j]=='K') {
				b1[j]=13;
			}else if(a1[j]=='A') {
				b1[j]=1;
			}
		}
		int s=0;
		int s1=0;
		for(int i=0;i<b.length;i++) {
			s+=b[i];
		}
		System.out.println("Black："+s);
		for(int i=0;i<b1.length;i++) {
			s1+=b1[i];
		}
		System.out.println("White："+s1);
		if(s<s1) {
			return -1;
		}
		else if(s==s1){return 0;}
             else return 1;
	}
		public void overCards(String cardsFirst, String cardsSecond) throws Exception {
		String s_first="";
		String s1_first="";
		String s_Second="";
		String s1_Second="";	
		//接收花色
		for(int i=1;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		//接收牌面值
		for(int i=0;i<cardsFirst.length();i+=2) {
			s1_first+=cardsFirst.charAt(i)+"";
		}
		//System.out.println(s_first+""+s1_first);//MRMMR22211
		//接收花色
		for(int i=1;i<cardsSecond.length();i+=2) {
			s_Second+=cardsSecond.charAt(i)+"";
		}
		//接收牌面值
		for(int i=0;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		//System.out.println(s_Second+""+s1_Second);//BMRRR12345
		for(int i=0;i<s1_first.length();i++) {//遍历牌1的牌值
			for(int k=0;k<s1_Second.length();k++) {//遍历牌2的牌值
				//System.out.println((s1_first.charAt(i)+"").equals(s1_Second.charAt(k)+""));
					if((s1_first.charAt(i)+"").equals(s1_Second.charAt(k)+"")) {//如果牌值相同
							if((s_first.charAt(i)+"").equals(s_Second.charAt(k)+"")) {//比较花色
								throw new  Exception("牌数不合法");
							}
					}
			}
		}
	}
	public int compareCards(String cardsFirst, String cardsSecond) throws Exception {
		//判断牌是否合法
		overCards(cardsFirst,cardsSecond);
		int flag=-1;
			System.out.println("**********Black的手牌为：***************");
			int f=getCardSign(cardsFirst);
			System.out.println("**********White的手牌为：*************");
			int s=getCardSign(cardsSecond);
			if(f>s) {
				flag=1;
			}else if(f==s) {

				System.out.println("两副牌等级相同，进行花色比较：");
				if(cardsFirst.equals(cardsSecond)) {
					System.out.println("同一副牌");
				}else {
						return ColorBord(cardsFirst,cardsSecond);
				}
			}
		return flag;
	}
}

