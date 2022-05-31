import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.io.*;

public class Notas implements ActionListener, ItemListener{
       JFrame f;
       Panel menu;
       Panel contador;
       Label columnas, lineas;
       JButton botones[];
       String LetraBotones[]={"M","m","Copiar","Cortar","Pegar"};
       TextArea campo;   
       MenuBar barra;
       Menu Archivo, Ayuda;
       MenuItem Nuevo, Abrir, Guardar_como, Salir, Version;
       String cadena;
       Choice tam;
       int opc;
       JFileChooser chooser;
       FileNameExtensionFilter filter;
       FileWriter fw;
       FileReader fr;
       String destino, texto;
       

public Notas(){
    //creacion de  objetos
    f=new JFrame("Notas");
    campo= new TextArea(); 
    menu= new Panel(new GridLayout());
    botones= new JButton[5];
    contador= new Panel(new GridLayout(1,4));
    columnas= new Label("Columnas: ");
    lineas= new Label("Lineas: ");
    tam=new Choice();
    
    //tamano
    tam.add("8");
    tam.add("10");
    tam.add("12");
    tam.add("14");
    tam.add("16");
    tam.add("18");
    tam.add("20");
    

    //menu
    barra= new MenuBar();
    Archivo=new Menu("Archivo");
    Ayuda=new Menu("Ayuda");
    Version=new MenuItem("Version: 1.4.8 by: Bryan");
    Nuevo=new MenuItem("Nuevo");
    Abrir=new MenuItem("Abrir");
    Guardar_como=new MenuItem("Guardar como");
    Salir=new MenuItem("Salir");
    
    //botones
    for(int i=0; i<2; i++){
    botones[i]= new JButton(LetraBotones[i]);
    menu.add(botones[i]);
}
    //imagenes en botones
    menu.add(tam);
    botones[2]= new JButton(new ImageIcon(getClass().getResource("\\imagenes\\Copiar.png")));
    menu.add(botones[2]);
    botones[3]= new JButton(new ImageIcon(getClass().getResource("\\imagenes\\Cortar.png")));
    menu.add(botones[3]);
    botones[4]= new JButton(new ImageIcon(getClass().getResource("\\imagenes\\Pegar.png")));
    menu.add(botones[4]);

    //acomodo del menu
    barra.add(Archivo);
    barra.add(Ayuda);
    Archivo.add(Nuevo);
    Archivo.add(Abrir);
    Archivo.add(Guardar_como);
    Archivo.add(Salir);
    Ayuda.add(Version);

    //contador
    contador.add(columnas);
    contador.add(lineas);
  

    //orejas
    botones[0].addActionListener(this);
    botones[1].addActionListener(this);
    botones[2].addActionListener(this);
    botones[3].addActionListener(this);
    botones[4].addActionListener(this);
    Nuevo.addActionListener(this);
    Salir.addActionListener(this);
    Abrir.addActionListener(this);
    Guardar_como.addActionListener(this);
    tam.addItemListener(this);
    
    //acomodo del frame
    f.setMenuBar(barra);
    f.add(contador, BorderLayout.SOUTH);
    f.add(menu, BorderLayout.NORTH);
    f.add(campo, BorderLayout.CENTER);
    f.setSize(700,700);
    f.setVisible(true);
}

public void actionPerformed(ActionEvent e){
    int p;
    if(e.getSource().equals(botones[2])){   //copiar
    cadena=campo.getSelectedText();

}else if(e.getSource().equals(botones[3])){   //cortar
    cadena=campo.getSelectedText();
    campo.replaceRange(" ", campo.getSelectionStart(),campo.getSelectionEnd());
  

}else if(e.getSource().equals(botones[4])){   //pegar
    p=campo.getCaretPosition();
    campo.insert(cadena,p);

}else if(e.getSource().equals(botones[0])){   //mayuscula
    cadena=campo.getSelectedText();
    campo.replaceRange(cadena.toUpperCase(),campo.getSelectionStart(),campo.getSelectionEnd());

}else if(e.getSource().equals(botones[1])){   //minuscula
    cadena=campo.getSelectedText();
    campo.replaceRange(cadena.toLowerCase(),campo.getSelectionStart(),campo.getSelectionEnd());

}else if(e.getSource().equals(Nuevo)){             //Nuevo
    campo.setText("");

}else if(e.getSource().equals(Salir)){            //Salir
    opc=JOptionPane.showConfirmDialog(f,"quieres salir sin guardar?", "salir", JOptionPane.YES_NO_CANCEL_OPTION);
    if(opc==0){
    System.exit(0);

    }else if(opc==1){
    chooser = new JFileChooser();
    filter = new FileNameExtensionFilter("Texto","txt","c","cpp","java","bat","py");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showSaveDialog(f);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
       System.out.println("You chose to open this file: " +
            chooser.getSelectedFile().getName());
    }  
    }

}else if(e.getSource().equals(Abrir)){


}else if(e.getSource().equals(Guardar_como)){
    chooser = new JFileChooser();
    filter = new FileNameExtensionFilter("Texto","txt","c","cpp","java","bat","py");
    chooser.setFileFilter(filter);
    int returnVal = chooser.showSaveDialog(f);
    if(returnVal == JFileChooser.APPROVE_OPTION) {
    try{
    File fichero = chooser.getSelectedFile();
    destino = fichero.toString();
    fw = new FileWriter(destino);

    texto = campo.getText();
    fw.write(texto);
    fw.close();

}catch(IOException f){
    System.out.println("Error E/S: " +f);
}
}
}
}

public void itemStateChanged(ItemEvent e){
     campo.setFont(new Font("",Font.PLAIN,Integer.parseInt(tam.getSelectedItem())));  //El set font tiene 3 perimetros fuente,(cursiva,negrita o normal) y tamano
 

}

public static void main(String args[]){
       Notas notas = new Notas();

}
}