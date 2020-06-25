import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserTest {
		public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("Black��");
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
				throw new  Exception("����ֵԽ�������쳣");
			}
		return str;
	}
	
	public int getCardSign(String cards) throws Exception {
		String s="";
		String s1="";
		//���ջ�ɫ
		for(int i=1;i<cards.length();i+=2) {
			s1+=cards.charAt(i)+"";
		}
		//��������ֵ
		for(int i=0;i<cards.length();i+=2) {
			s+=cards.charAt(i)+"";
		}
		//�жϻ�ɫ
		for(int i=0;i<s1.length();i++) {
			switch(s1.charAt(i)) {
			case 'S':
				System.out.println("����"+getCardArr(s.charAt(i)));
				break;
			case 'H':
				System.out.println("����"+getCardArr(s.charAt(i)));
				break;
			case 'C':
				System.out.println("÷��"+getCardArr(s.charAt(i)));
				break;
			case 'D':
				System.out.println("����"+getCardArr(s.charAt(i)));
				break;
			default:
				throw new  Exception("��ɫֵԽ�������쳣");
			}
		}
		System.out.print("����Ϊ��");
		return getCardslevel(s,s1);
	}
	
	public int getCardslevel(String s,String s1) {
		//count_s����ͳ��������ֵͬ
		int count_s=0;
		//level�������õȼ���Ĭ��Ϊ0
		int level = 0;
		//�����ַ���
		for(int i=0;i<s.length();i++) {
			for(int j=i+1;j<s.length();j++) {
				//�Ƚ�ֵ�Ƿ���ͬ
				if((s.charAt(i)+"").equals((s.charAt(j)+""))){
					count_s++;
				}
			}
		}
		switch(count_s) {
		case 1://������ͬ
			level=1;
			System.out.println("һ��");
			break;
		case 2://��������ͬ
			level=2;
			System.out.println("����");
			break;
		case 3://������ͬ
			level=3;
			System.out.println("����");
			break;
		case 4://3+2
			level=6;
			System.out.println("��«");
			break;
		case 6://������ͬ
			level=7;
			System.out.println("����");
			break;
		default:
			level=getCardsColor(s,s1);
		}
		System.out.println("level="+level+"\n");
		return level;
	}
	public int getCardsColor(String s,String s1) {
		//count_s1����ͳ�ƻ�ɫ��ֵͬ
		int count_s1=0;
		int level=0;
		//�����ַ���
		for(int i=0;i<s1.length();i++) {
			for(int j=i+1;j<s1.length();j++) {
			//�Ƚ�ֵ�Ƿ���ͬ
				if((s1.charAt(i)+"").equals((s1.charAt(j)+""))){
					count_s1++;
				}
			}
		}
		switch(count_s1) {
		case 10:
			level=5;
			System.out.print("ͬ��");
			if(transition(s)==true) {
				level=8;
				System.out.println("˳");
			}else {
				System.out.println();
			}
			break;
		default:
			if(transition(s)==true) {
				level=4;
				System.out.println("˳��");
			}else {
				System.out.println("����");
			}
		}
		return level;
	}
	public boolean transition(String s) {
		char[] a=s.toCharArray();
		int[] b=new int[a.length];
		for(int j=0;j<a.length;j++) {
			if (Character.isDigit(a[j])){  // �ж��Ƿ�������
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
        // ��������
        Arrays.sort(b);
        //����Ԫ�ؼ�����ִ����洢�� map ��
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : b) {
        	//��Ԫ�ش洢��map���洢��Ĭ��ֵΪ1
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (int h : b) {
        	//�����жϸü���Ĭ��ֵ�Ƿ����0
            if (map.get(h) > 0) {
                for (int j = 0; j < length; j++) {
                	 // �ж� map ����hΪ�׵����������ǲ�������Ԫ�ع���˳��
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
		System.out.println("Black��");
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
		System.out.println("White��");
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
//		System.out.println("�ظ���ɫ��Ϊ��"+count+"===="+count1);
		if(count<count1) {
			return -1;
		}else if(count==count1) {
			if(b[n]<b1[n]) {
				return -1; 
			}else if(b[n]==b1[n]) {
				System.out.println("�����Ƶȼ���ͬ����ɫ�ȼ�Ҳ��ͬ�������ܵ����Ƚϣ�");
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
			if (Character.isDigit(a[j])){  // �ж��Ƿ�������
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
			if (Character.isDigit(a1[j])){  // �ж��Ƿ�������
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
		System.out.println("Black��"+s);
		for(int i=0;i<b1.length;i++) {
			s1+=b1[i];
		}
		System.out.println("White��"+s1);
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
		//���ջ�ɫ
		for(int i=1;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		//��������ֵ
		for(int i=0;i<cardsFirst.length();i+=2) {
			s1_first+=cardsFirst.charAt(i)+"";
		}
		//System.out.println(s_first+""+s1_first);//MRMMR22211
		//���ջ�ɫ
		for(int i=1;i<cardsSecond.length();i+=2) {
			s_Second+=cardsSecond.charAt(i)+"";
		}
		//��������ֵ
		for(int i=0;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		//System.out.println(s_Second+""+s1_Second);//BMRRR12345
		for(int i=0;i<s1_first.length();i++) {//������1����ֵ
			for(int k=0;k<s1_Second.length();k++) {//������2����ֵ
				//System.out.println((s1_first.charAt(i)+"").equals(s1_Second.charAt(k)+""));
					if((s1_first.charAt(i)+"").equals(s1_Second.charAt(k)+"")) {//�����ֵ��ͬ
							if((s_first.charAt(i)+"").equals(s_Second.charAt(k)+"")) {//�Ƚϻ�ɫ
								throw new  Exception("�������Ϸ�");
							}
					}
			}
		}
	}
	public int compareCards(String cardsFirst, String cardsSecond) throws Exception {
		//�ж����Ƿ�Ϸ�
		overCards(cardsFirst,cardsSecond);
		int flag=-1;
			System.out.println("**********Black������Ϊ��***************");
			int f=getCardSign(cardsFirst);
			System.out.println("**********White������Ϊ��*************");
			int s=getCardSign(cardsSecond);
			if(f>s) {
				flag=1;
			}else if(f==s) {

				System.out.println("�����Ƶȼ���ͬ�����л�ɫ�Ƚϣ�");
				if(cardsFirst.equals(cardsSecond)) {
					System.out.println("ͬһ����");
				}else {
						return ColorBord(cardsFirst,cardsSecond);
				}
			}
		return flag;
	}
}

