
public class Central_RE
{
  protected String type;
  protected int value;
  
  public Central_RE(String name, String t, int v)
  {
    super(name);
    type = t;
    value = v;
  }
  
  public String toString()
  {
    return "La centrale "+name+", de type : "+type+", produit "+value+" KW/s.";
  }
  
  public String getType()
  {
    return type;
  }
  
  public int getValue()
  {
   return value;
  }
  
}
