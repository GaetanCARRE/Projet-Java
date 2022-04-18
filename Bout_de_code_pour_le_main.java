import java.util.Arrays;
// À placer dans le main
...
{
  Components[] C...;
  ...
  Arrays.sort(C, String.CASE_INSENSITIVE_ORDER);//case insensitive sort     
  for(int i = 0; i < C.length; i++)
  {
    if(!( C[i] instanceof Building))
      System.out.print( "- "+C[i]+ "\n");
  }
}
...
