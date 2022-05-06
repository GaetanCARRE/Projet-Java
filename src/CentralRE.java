public class CentralRE extends Building
{
  protected String type;
  protected int value;

  public CentralRE(String id, String name, String t, int v)
  {
    super(id,name);
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
