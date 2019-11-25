import java.io.*;
import java.util.*;
public class RatInMaze{
	static boolean[][]visited;
	static int n;
	static List<List<Character>>paths;
	static int[][]matrix;
	public static void findPath(int i, int j, List<Character>path){
		if(i==n-1 && j==n-1){
			paths.add(new ArrayList(path));
			return;
		}

		visited[i][j]=true;

		if(isValid(i+1,j)){
			path.add('D');
			findPath(i+1,j,path);
			path.remove(path.size()-1);
		}

		if(isValid(i,j+1)){
			path.add('R');
			findPath(i,j+1,path);
			path.remove(path.size()-1);
		}

		if(isValid(i,j-1)){
			path.add('L');
			findPath(i,j-1,path);
			path.remove(path.size()-1);
		}

		if(isValid(i-1,j)){
			path.add('U');
			findPath(i-1,j,path);
			path.remove(path.size()-1);
		}

		visited[i][j]=false;

	}

	public static boolean isValid(int x, int y){
		return 0<=x && x<n && 0<=y && y<n && matrix[x][y]==1 && !visited[x][y];
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		String[]in=br.readLine().split(" ");
		matrix=new int[n][n];
		visited=new boolean[n][n];
		paths=new ArrayList<List<Character>>();
		int k=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				matrix[i][j]=Integer.parseInt(in[k]);
				k++;
			}
		}

		findPath(0,0,new ArrayList<Character>());
		List<String>result=new ArrayList<String>();
		for(int i=0;i<paths.size();i++){
			result.add(paths.get(i).toString());
		}
		Collections.sort(result);
		System.out.println(result);
	}
}