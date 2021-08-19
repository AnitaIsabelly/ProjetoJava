public class Main
{
  public static void main(String[] args)
  {
    CriaLabirinto texto = new CriaLabirinto();
    char[][] labirinto;
    
    try{
      labirinto = texto.labirinto();
      Labirinto lab = new Labirinto(labirinto);

      System.out.println(lab.toString());

      lab.encontrarAdj();
      lab.andadinha();
      System.out.println("\n\n\n");
      System.out.println(lab.toString());

      Pilha<Coordenada> caminho = lab.getCaminho();
      Pilha<Coordenada> inverso = new Pilha<Coordenada>();
      while(!caminho.isVazia())
      {
        inverso.guardeUmItem(caminho.recupereUmItem());
        caminho.removaUmItem();
      }
      System.out.print("Caminho percorrido: ");
      while(!inverso.isVazia())
      {
        System.out.print(inverso.recupereUmItem().toString() + " ");
        inverso.removaUmItem();
      }

    }
    catch(Exception erro1){
      System.err.println(erro1.getMessage());
    }
  }
}