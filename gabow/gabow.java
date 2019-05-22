/**
 *     Java Program to Implement Gabow Algorithm
        by: sumihar christian
 **/
 
import java.util.*;
 
/** class Gabow **/
class Gabow
{
    /** number of vertices **/
    private int V;    
    /** preorder number counter **/
    private int preCount;
    private int[] preorder;
    /** to check if v is visited **/
    private boolean[] visited;  
    /** check strong componenet containing v **/
    private boolean[] chk;    
    /** to store given graph **/
    private List<Integer>[] graph;
    /** to store all scc **/
    private List<List<Integer>> sccComp;
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
 
    /** function to get all strongly connected components **/
    public List<List<Integer>> getComponent(List<Integer>[] graph) 
    {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
        V = graph.length;
        preorder = new int[V];
        visited = new boolean[V];
        chk = new boolean[V]; 
        sccComp = new ArrayList<>();
        this.graph = graph;
 
        for (int v = 0; v < V; v++)
              if (!visited[v])
                dfs(v);
 
        return sccComp;
      }


    /** function dfs **/
    
    
    public void dfs(int x) 
    {
        preorder[x] = preCount++;
        visited[x] = true;
        stack1.push(x);
        stack2.push(x);
 
        for (int w : graph[x]) 
        {
            if (!visited[w])
                dfs(w);
            else if (!chk[w]) 
                while (preorder[stack2.peek()] > preorder[w])
                    stack2.pop();            
        }
        if (stack2.peek() == x) 
        {
            stack2.pop();
            List<Integer> component = new ArrayList<Integer>();
            int w;
            do 
            {
                w = stack1.pop();
                component.add(w);
                chk[w] = true;
            } while (w != x);  
            sccComp.add(component);            
        }                       
    }    

    /** main **/
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Gabow algorithm Test\n");
        System.out.println("Enter number of Vertices");
        /** jumlah vertices **/
        int V = scan.nextInt();
 
        /** buat graph **/
        List<Integer>[] g = new List[V];        
        for (int i = 0; i < V; i++)
            g[i] = new ArrayList<Integer>();        
        System.out.println("\nEnter number of edges");
        int E = scan.nextInt();
        /** semua edges **/
        System.out.println("Enter "+ E +" x, y coordinates");
        for (int i = 0; i < E; i++)
        {
            int x = scan.nextInt();
            int y = scan.nextInt();
            g[x].add(y);
        }    
 
        Gabow gab = new Gabow();        
        System.out.println("\nSCC : ");
        /** print all scc **/
        List<List<Integer>> scComponents = gab.getComponent(g);
        System.out.println(scComponents);        
    }    
}