package Pokemon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;



/**
 *
 * @author Marcos González Villalba
 * 
 * Los párrafos comentados son un intento fallido de una parte de la aplicación
 * que ya te preguntaré en clase porque me gustaría saber resolverlo.
 */
public class VentanaPokedex extends javax.swing.JFrame {

    private BufferedImage buffer;
    private BufferedImage bufferEvolucion;
    private Image imagenPokemons;
    //private int contadorEvolucion;
    private int contador = 0;
    private int ancho = 200, alto = 200;
    boolean encontrado = false;
    //int contador2 = 0;
    //Pokemon p = new Pokemon();
    
    //ArrayList<Integer> familiasPokemons = new ArrayList<Integer>();

    
    //////////////////////////////////////////////////////////////
    
    //Conecttamos a la base de datos
    private Statement estado;
    private ResultSet resultadoConsulta;
    private Connection conexion;
    
    
    
    /////////////////////////////////////////////////////////////
    //declaro el hashmap para almacenar el resultado de la consulta
    HashMap <String,Pokemon> listaPokemons = new HashMap();
    
    /**
     * Creates new form VentanaPokedex
     */
    /**
     * Creates new form VentanaPokedex
     */
    private void dibujaElPokemonQueEstaEnLaPosicion (int posicion){
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        //borro lo que hubiera
        g2.setColor(Color.white);
        g2.fillRect(0, 0, alto, ancho);
        g2.drawImage(imagenPokemons,
                0,
                0,
                ancho,
                alto,
                96*columna,
                96*fila,
                96*columna + 96,
                96*fila + 96,
                null);
        repaint();
        escribeDatos();
    }
    
    private void dibujaMiniatura (int posicion){
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) bufferEvolucion.getGraphics();
        //borro lo que hubiera
        Color c = new Color(0.27f,0.51f,0.70f,1f );
        g2.setColor(c);
        g2.fillRect(0, 0, alto, ancho);
        g2.drawImage(imagenPokemons,
                0,
                0,
                75,
                75,
                96*columna,
                96*fila,
                96*columna + 96,
                96*fila + 96,
                null);
        repaint();
        //escribeDatos();
    }
    
    
    private void escribeDatos(){
        Pokemon p = listaPokemons.get(String.valueOf(contador+1));
        if (p != null){
            jLabel1.setText(p.nombre);
            jLabel2.setText(p.species);
            jLabel3.setText(p.habitat);
            jLabel9.setText(p.color);
            jLabel10.setText(p.nombre);
            
        }
        else{
            jLabel1.setText("NO HAY DATOS");
            jLabel2.setText("NO HAY DATOS");
            jLabel3.setText("NO HAY DATOS");
            jLabel9.setText("NO HAY DATOS");
            jLabel10.setText("No hay datos");
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0,alto,ancho, null);
        Graphics2D g3 = (Graphics2D) jPanel2.getGraphics();
        g3.drawImage(bufferEvolucion, 0, 0,alto,ancho, null);
            
    }
    
    
   
    public VentanaPokedex() {
        initComponents();
        
        setSize(jLabel4.getWidth(), jLabel4.getHeight());
        
        try {
            imagenPokemons = ImageIO.read(getClass().getResource("black-white.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buffer =(BufferedImage) jPanel1.createImage(ancho,alto);
        Graphics2D g2 = buffer.createGraphics();
        
        bufferEvolucion =(BufferedImage) jPanel2.createImage(ancho,alto);
        Graphics2D g3 = bufferEvolucion.createGraphics();

        
        
        
        
        
        ///Conexion a la base de datos///////////////////////////////////
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test", "root", "");
            estado = conexion.createStatement();
            resultadoConsulta = estado.executeQuery("Select * from pokemon");
            //cargo el resultado del Query en mi hashmap
            while (resultadoConsulta.next()){
                Pokemon p = new Pokemon();
                p.nombre = resultadoConsulta.getString(2);
                p.generation_id = resultadoConsulta.getInt(5);
                p.evolution_change_id = resultadoConsulta.getInt(6);
                p.species = resultadoConsulta.getString(12);
                p.habitat = resultadoConsulta.getString(15);
                p.evolution_parent_pokemon_id = resultadoConsulta.getInt(7);
                p.color = resultadoConsulta.getString(13);
                p.id = resultadoConsulta.getInt(1);
                
                //lo guardo en el hashmap
                listaPokemons.put(resultadoConsulta.getString(1), p);
                
            }
        }
        catch (Exception e){
            
        }
        ////////////////////////////////////////////////////////////////
        dibujaElPokemonQueEstaEnLaPosicion(0);
        dibujaMiniatura(0);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(255, 204, 51));
        jButton1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton1.setText("Anterior");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(445, 493, 120, 60);

        jButton2.setBackground(new java.awt.Color(255, 204, 51));
        jButton2.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jButton2.setText("Siguiente");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(571, 493, 120, 60);

        jLabel1.setText("Nombre");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(520, 220, 130, 20);

        jLabel2.setText("Tipo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(520, 260, 132, 20);

        jLabel3.setText("Entorno");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(520, 240, 109, 20);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(80, 200, 200, 170);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(470, 340, 80, 60);

        jLabel5.setText("Nombre:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(460, 220, 80, 20);

        jLabel6.setText("Hábitat:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(460, 240, 60, 20);

        jLabel7.setText("Tipo:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(460, 260, 60, 20);

        jLabel8.setText("Color:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(460, 280, 60, 20);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Color");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(520, 280, 110, 20);

        jLabel10.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("NOMBRE");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(90, 500, 120, 40);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pokemon/pokedexEditada.gif"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 790, 640);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        contador--;
        if (contador < 0) contador = 0;
        dibujaElPokemonQueEstaEnLaPosicion(contador);
        dibujaMiniatura(contador);
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        contador++;
        if (contador > 507) {contador = 0;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);
        
        dibujaMiniatura(contador);
    }//GEN-LAST:event_jButton2MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
