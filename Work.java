package homework;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**  
 * @ClassName: Work
 * @Description: 1.统计代码行数
		给一个文件目录,遍历该目录下的所有文件,如果是.java文件,利用字符流读取该java源文件,统计全部java文件的代码共有多少行,java文件个数有多少
		 提示：先获取所有文件，如果文件名以java结尾则表示是Java文件。然后再计算所有Java文件的行数总
		合，以及Java文件的个数。
 * @author LYL
 * @date 2021-01-13 11:32:40
*/

public class Work {
	
	public static void main(String[] args) {
		//需要扫描统计的路径
		File file = new File("C:\\Test");
		String str = null;
		try {
			//调用scan方法并拿到返回的字符串
			str = scan(file,0,0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("扫描完毕");
		String[] str2 = str.split(",");
		//将返回出的字符串解析为数字
		int lineNum = Integer.parseInt(str2[0]);
		int fileNum = Integer.parseInt(str2[1]);
		
		System.out.println("行数为："+lineNum+"，文件数为："+fileNum);
	}
	
	public static String scan(File f,int LineNumber, int FileNumber) throws IOException {
		int lineNum = LineNumber;
		int fileNum = FileNumber;
		// 将传入的File对象变成File数组
		File[] lf = f.listFiles();
		// 如果为空则结束这次方法。避免空指针异常
		if (lf == null) {
			return null;
		}
		// 循环遍历lf中的每个File对象
		for (File f1 : lf) {
			// 如果当前遍历到的这个File对象是文件夹
			if (f1.isDirectory()) {
				// 得到当前文件夹的路径
				String path = f1.getAbsolutePath();
				// 重新调用当前方法，并传入刚刚遍历到的文件夹对象，行数和文件数，并用一个String接收返回的字符串
				String test = scan(new File(path),lineNum,fileNum);
				
				String[] str2 = test.split(",");
				//将拿到的返回出的字符串解析为行数和文件数
				lineNum = Integer.parseInt(str2[0]);
				fileNum = Integer.parseInt(str2[1]);
				// 如果当前File对象是一个文件
			} else {
				//判断是不是java文件
				if(f1.getName().endsWith(".java")) {
					fileNum++;
				}
				// 输出当前文件的名称
				//System.out.println(f1.getName());
				//创建当前文件的对象
				File file = new File(f1.getAbsolutePath());
				
				FileReader fr = null;
				
				//创建字符流
				fr = new FileReader(file);
				int i = 0;
				while((i = fr.read())!=-1) {
					Character c = (char)i;
					//将读出的字符转换为字符串
					String temp = c.toString();
					//判断字符串中有没有换行
					if(temp.contains("\n")) {
						lineNum++;
					}
				}
				//关闭字符流
				fr.close();
			}
		}
		System.out.println("lineNum = "+lineNum+", fileNum = "+fileNum);
		//将行数和文件数返回
		return lineNum+","+fileNum;
	}
}

