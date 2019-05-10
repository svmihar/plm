classdef GabowSSC < handle
   properties 
      marked = [];
      id = [];
      preorder = [];
      pre = 0;
      count = 0;
      stack1 = java.util.Stack();
      stack2 = java.util.Stack();
   end
   methods
      function ssc(obj,matrix1, matrix2)
          G = digraph(matrix1,matrix2); 
          A = table2array(G.Edges);
          [z,x] = size(A);
          maks = 0;
          for i = 1:1:z
              for j = 1:1:x
                  if maks<A(i,j)
                      maks = A(i,j);
                  end
              end
          end
          obj.marked = zeros(1,maks);
          obj.preorder = zeros(1,maks);
          for v=1:1:maks
              obj.id(v) = -1;
          end
          for v=1:1:maks
              if ~obj.marked(v)
                  dfs(obj,matrix1,matrix2,v);
              end
          end
      end
      function dfs(obj,matrix1,matrix2,v)
          obj.marked(v) = true;
          obj.preorder(v) = obj.pre;
          obj.pre = obj.pre+1;
          obj.stack1.push(v);
          obj.stack2.push(v);
          for w=adj(obj,matrix1,matrix2,v)
              if ~obj.marked(w)
                  dfs(obj,matrix1,matrix2,w);
              elseif obj.id(w)==-1
                  while obj.preorder(obj.stack2.peek)>obj.preorder(w)
                      obj.stack2.pop;
                  end
              end
          end
          if obj.stack2.peek == v
              obj.stack2.pop;
              w = obj.stack1.pop;
              obj.id(w) = obj.count;
              while ~w==v
              w = obj.stack1.pop;
              obj.id(w) = obj.count;
              end
              obj.count = obj.count+1
          end
          
      end
      function a = ids(obj,v)
          a = obj.id(v);
      end
      function c = counts(obj)
          c = obj.count;
      end
      function c = adj(~,matrix1,matrix2,v)
          G = digraph(matrix1,matrix2); 
          A = table2array(G.Edges);
          [a,b] = size(A);
          maks = 0;
          for i = 1:1:a
              for j = 1:1:b
                  if maks<A(i,j)
                      maks = A(i,j);
                  end
              end
          end
          B = zeros(maks);
          for i = 1:1:a
              B(A(i,1),A(i,2))=1;
          end
          C = B(v,:);
          counter = 1;
          c = [];
          for i = 1:1:maks
              if C(1,i) == 1
                  c(counter) = i; 
                  counter = counter+1;
              end
          end
      end
   end
end