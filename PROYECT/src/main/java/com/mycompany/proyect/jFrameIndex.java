/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JTable;

/**
 *
 * @author jose
 */
public class jFrameIndex extends javax.swing.JFrame {

    public static DefaultTableModel m;
    public static DefaultTableModel ml;
    public static DefaultTableModel mp;
    public static DefaultTableModel md;
    public static DefaultTableModel mbu;

    /**
     * Creates new form NewJFrame
     */
    public jFrameIndex() {
        initComponents();
        preparaTablaUsuario();
        preparaTablaLibro();
        preparaTablasPrestamo();
        preparaTablasLibDisp();
    }
    
    private void preparaTablaUsuario(){
        String titulos[] = {"NOMBRE", "APELLIDOS", "DNI", "DOMICILIO", "TELEFONO"};
        m = new DefaultTableModel(null, titulos);
        jTableUsuarios.setModel(m);
        jTableUsuarios.setDefaultEditor(Object.class, null);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            String query = "SELECT NOMBRE, APELLIDOS, DNI, DOMICILIO, TELEFONO FROM usuarios";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            m.setRowCount(0);
            while(rs.next()){
                String nombre = rs.getString("NOMBRE");
                String apellidos = rs.getString("APELLIDOS");
                String dni = rs.getString("DNI");
                String domicilio = rs.getString("DOMICILIO");
                int telefono = rs.getInt("TELEFONO");

                m.addRow(new Object[]{nombre, apellidos, dni, domicilio, telefono});
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception ex){
            System.out.println("No Conectado. Error:");
            ex.printStackTrace();
        }
    }
    
    void preparaTablaAyudaUsuario(JTable tabla){
        String titulos[] = {"NOMBRE", "DNI", "TELEFONO"};
        mbu = new DefaultTableModel(null, titulos);
        tabla.setModel(mbu);
        tabla.setDefaultEditor(Object.class, null);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            String query = "SELECT CONCAT(NOMBRE,' ', APELLIDOS) as NOMBRE , DNI, TELEFONO FROM usuarios";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            mbu.setRowCount(0);
            while(rs.next()){
                String nombre = rs.getString("NOMBRE");
                String dni = rs.getString("DNI");
                int telefono = rs.getInt("TELEFONO");

                mbu.addRow(new Object[]{nombre, dni, telefono});
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception ex){
            System.out.println("No Conectado. Error:");
            ex.printStackTrace();
        }
    }


    private void preparaTablaLibro(){
        String titulos[] = {"ID", "TITULO", "AUTOR", "TIPO", "DISPONIBLE"};
        ml = new DefaultTableModel(null, titulos);
        jTableLibro.setModel(ml);
        jTableLibro.setDefaultEditor(Object.class, null);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            String query = "SELECT ID, TITULO, AUTOR, TIPO, DISPONIBLE FROM libros";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ml.setRowCount(0);
            while(rs.next()){
                int id = rs.getInt("ID");
                String titulo = rs.getString("TITULO");
                String autor = rs.getString("AUTOR");
                String tipo = rs.getString("TIPO");
                Boolean disponible = rs.getInt("DISPONIBLE") > 0;
                
                ml.addRow(new Object[]{id, titulo, autor, tipo, disponible});
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception ex){
            System.out.println("No Conectado. Error:");
            ex.printStackTrace();
        }
    }
        
    private void preparaTablasPrestamo(){
        String titulos[] = {"ID_LIBRO", "NOMBRE_LIBRO", "NOMBRE_CLIENTE", "DNI"};
        mp = new DefaultTableModel(null, titulos);
        jTableDevolucion.setModel(mp);
        jTableDevolucion.setDefaultEditor(Object.class, null);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            String query = "SELECT l.ID as id, l.TITULO as titulo, CONCAT(u.NOMBRE, ' ', u.APELLIDOS) as nombre, u.DNI as dni FROM usuarios u JOIN libros l ON l.DNI_prestado = u.DNI WHERE l.DISPONIBLE = 0;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            mp.setRowCount(0);
            while(rs.next()){
                Integer id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String nombre = rs.getString("nombre");
                String dni = rs.getString("dni");

                mp.addRow(new Object[]{id, titulo, nombre, dni});
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception ex){
            System.out.println("No Conectado. Error:");
            ex.printStackTrace();
        }
    }
        
    void preparaTablasLibDisp(){
        String titulos[] = {"ID_LIBRO", "TITULO_LIBRO", "AUTOR"};
        md = new DefaultTableModel(null, titulos);
        jTablePrestar.setModel(md);
        jTablePrestar.setDefaultEditor(Object.class, null);
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            String query = "SELECT l.ID as id, l.TITULO as titulo, l.AUTOR as autor FROM libros l WHERE l.DISPONIBLE > 0;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            md.setRowCount(0);
            while(rs.next()){
                Integer id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");

                md.addRow(new Object[]{id, titulo, autor});
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(Exception ex){
            System.out.println("No Conectado. Error:");
            ex.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog2 = new javax.swing.JDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelEncabezado = new javax.swing.JPanel();
        jLabelTituloEncabezado = new javax.swing.JLabel();
        jLabelFechaEncabezado1 = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelPrincipal = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabelImagenInicio = new javax.swing.JLabel();
        jPanelPrestamos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFielPrestarUsu = new javax.swing.JTextField();
        jButtonPrestar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePrestar = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButtonEnseñaUsuario = new javax.swing.JButton();
        jPanelUsuarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jLabelUsuariosBuscar = new javax.swing.JLabel();
        jTextFieldBuscarUsuarios = new javax.swing.JTextField();
        jButtonUsuariosBuscar = new javax.swing.JButton();
        jButtonEliminarUsuario = new javax.swing.JButton();
        jButtonUsuariosAnadir = new javax.swing.JButton();
        jComboBoxTipoBusquedaUsuarios = new javax.swing.JComboBox<>();
        jButtonEditarUsuarios = new javax.swing.JButton();
        jPanelLibros = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableLibro = new javax.swing.JTable();
        jLabelLibroTitulo = new javax.swing.JLabel();
        jTextFieldBuscarLibro = new javax.swing.JTextField();
        jButtonUsuariosLibro = new javax.swing.JButton();
        jButtonEliminarLibro = new javax.swing.JButton();
        jButtonLibroAnadir = new javax.swing.JButton();
        jComboBoxTipoLibro = new javax.swing.JComboBox<>();
        jButtonEditarLibro = new javax.swing.JButton();
        jPanelDevolucion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelFotoPrestamo1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDevolverLib = new javax.swing.JTextField();
        jTextFielDevolverUsu = new javax.swing.JTextField();
        jButtonDevolver = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableDevolucion = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(680, 600));
        getContentPane().setLayout(null);

        jPanelEncabezado.setBackground(new java.awt.Color(0, 204, 255));
        jPanelEncabezado.setLayout(null);

        jLabelTituloEncabezado.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelTituloEncabezado.setForeground(new java.awt.Color(0, 0, 0));
        jLabelTituloEncabezado.setText("Administracion/Control/Biblioteca");
        jPanelEncabezado.add(jLabelTituloEncabezado);
        jLabelTituloEncabezado.setBounds(0, 0, 380, 30);

        jLabelFechaEncabezado1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelFechaEncabezado1.setForeground(new java.awt.Color(0, 0, 0));
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fechaFormateada = fechaActual.format(formatter);
        jLabelFechaEncabezado1.setText("hoy es " + fechaFormateada);
        jPanelEncabezado.add(jLabelFechaEncabezado1);
        jLabelFechaEncabezado1.setBounds(0, 30, 390, 30);

        getContentPane().add(jPanelEncabezado);
        jPanelEncabezado.setBounds(0, 0, 660, 60);

        jPanelPrincipal.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Pagina Principal");
        jPanelPrincipal.add(jLabel2);
        jLabel2.setBounds(230, 10, 190, 50);

        jLabelImagenInicio.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\Downloads\\PROYECT_V-1.0\\PROYECT_V-1.0\\PROYECT\\src\\main\\java\\com\\mycompany\\proyect\\png-clipart-school-library-drawing-school-child-book.png")); // NOI18N
        jPanelPrincipal.add(jLabelImagenInicio);
        jLabelImagenInicio.setBounds(130, 50, 390, 380);

        jTabbedPane.addTab("tab1", jPanelPrincipal);

        jPanelPrestamos.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Prestamos");
        jPanelPrestamos.add(jLabel1);
        jLabel1.setBounds(10, 10, 120, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\Downloads\\PROYECT_V-1.0\\PROYECT_V-1.0\\PROYECT\\src\\main\\java\\com\\mycompany\\proyect\\Prestar.png")); // NOI18N
        jPanelPrestamos.add(jLabel3);
        jLabel3.setBounds(370, 20, 260, 180);

        jTextFielPrestarUsu.setText("Ingrese DNI usuario");
        jTextFielPrestarUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFielPrestarUsuMouseClicked(evt);
            }
        });
        jPanelPrestamos.add(jTextFielPrestarUsu);
        jTextFielPrestarUsu.setBounds(20, 80, 210, 50);

        jButtonPrestar.setText("Prestar");
        jButtonPrestar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrestarActionPerformed(evt);
            }
        });
        jPanelPrestamos.add(jButtonPrestar);
        jButtonPrestar.setBounds(20, 160, 210, 40);

        jTablePrestar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTablePrestar);

        jPanelPrestamos.add(jScrollPane3);
        jScrollPane3.setBounds(0, 250, 660, 240);

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 2, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tabla de los libros disponibles actualmente");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelPrestamos.add(jLabel7);
        jLabel7.setBounds(0, 210, 660, 30);

        jButtonEnseñaUsuario.setBackground(new java.awt.Color(204, 204, 0));
        jButtonEnseñaUsuario.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jButtonEnseñaUsuario.setForeground(new java.awt.Color(0, 0, 0));
        jButtonEnseñaUsuario.setText("?");
        jButtonEnseñaUsuario.setAlignmentY(0.0F);
        jButtonEnseñaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnseñaUsuarioActionPerformed(evt);
            }
        });
        jPanelPrestamos.add(jButtonEnseñaUsuario);
        jButtonEnseñaUsuario.setBounds(240, 80, 25, 30);

        jTabbedPane.addTab("tab2", jPanelPrestamos);

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableUsuarios);

        jLabelUsuariosBuscar.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabelUsuariosBuscar.setText("Usuarios");

        jTextFieldBuscarUsuarios.setText("Busqueda");
        jTextFieldBuscarUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarUsuariosMouseClicked(evt);
            }
        });
        jTextFieldBuscarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarUsuariosActionPerformed(evt);
            }
        });

        jButtonUsuariosBuscar.setText("Buscar");
        jButtonUsuariosBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuariosBuscarActionPerformed(evt);
            }
        });

        jButtonEliminarUsuario.setBackground(new java.awt.Color(204, 0, 0));
        jButtonEliminarUsuario.setText("Eliminar");
        jButtonEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarUsuarioActionPerformed(evt);
            }
        });

        jButtonUsuariosAnadir.setBackground(new java.awt.Color(0, 153, 153));
        jButtonUsuariosAnadir.setForeground(new java.awt.Color(0, 0, 0));
        jButtonUsuariosAnadir.setText("Anadir");
        jButtonUsuariosAnadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonUsuariosAnadirMouseClicked(evt);
            }
        });
        jButtonUsuariosAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuariosAnadirActionPerformed(evt);
            }
        });

        jComboBoxTipoBusquedaUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "APELLIDOS", "DNI", "TELEFONO" }));
        jComboBoxTipoBusquedaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoBusquedaUsuariosActionPerformed(evt);
            }
        });

        jButtonEditarUsuarios.setBackground(new java.awt.Color(255, 153, 0));
        jButtonEditarUsuarios.setText("Editar");
        jButtonEditarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelUsuariosLayout = new javax.swing.GroupLayout(jPanelUsuarios);
        jPanelUsuarios.setLayout(jPanelUsuariosLayout);
        jPanelUsuariosLayout.setHorizontalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                        .addComponent(jButtonUsuariosAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditarUsuarios)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarUsuario))
                    .addComponent(jLabelUsuariosBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelUsuariosLayout.createSequentialGroup()
                        .addComponent(jComboBoxTipoBusquedaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBuscarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUsuariosBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUsuariosLayout.setVerticalGroup(
            jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelUsuariosBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUsuariosBuscar)
                    .addComponent(jComboBoxTipoBusquedaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonUsuariosAnadir)
                    .addComponent(jButtonEliminarUsuario)
                    .addComponent(jButtonEditarUsuarios))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("tab3", jPanelUsuarios);

        jTableLibro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTableLibro);

        jLabelLibroTitulo.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        jLabelLibroTitulo.setText("Libros");

        jTextFieldBuscarLibro.setText("Busqueda");
        jTextFieldBuscarLibro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarLibroMouseClicked(evt);
            }
        });
        jTextFieldBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscarLibroActionPerformed(evt);
            }
        });

        jButtonUsuariosLibro.setText("Buscar");
        jButtonUsuariosLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUsuariosLibroActionPerformed(evt);
            }
        });

        jButtonEliminarLibro.setBackground(new java.awt.Color(204, 0, 0));
        jButtonEliminarLibro.setText("Eliminar");
        jButtonEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarLibroActionPerformed(evt);
            }
        });

        jButtonLibroAnadir.setBackground(new java.awt.Color(0, 153, 153));
        jButtonLibroAnadir.setForeground(new java.awt.Color(0, 0, 0));
        jButtonLibroAnadir.setText("Anadir");
        jButtonLibroAnadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonLibroAnadirMouseClicked(evt);
            }
        });
        jButtonLibroAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLibroAnadirActionPerformed(evt);
            }
        });

        jComboBoxTipoLibro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "TITULO", "AUTOR", "TIPO" }));
        jComboBoxTipoLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoLibroActionPerformed(evt);
            }
        });

        jButtonEditarLibro.setBackground(new java.awt.Color(255, 153, 0));
        jButtonEditarLibro.setText("Editar");
        jButtonEditarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLibrosLayout = new javax.swing.GroupLayout(jPanelLibros);
        jPanelLibros.setLayout(jPanelLibrosLayout);
        jPanelLibrosLayout.setHorizontalGroup(
            jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLibrosLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLibrosLayout.createSequentialGroup()
                        .addComponent(jButtonLibroAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEditarLibro)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarLibro))
                    .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelLibroTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelLibrosLayout.createSequentialGroup()
                            .addComponent(jComboBoxTipoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButtonUsuariosLibro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanelLibrosLayout.setVerticalGroup(
            jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLibrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLibroTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBuscarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUsuariosLibro)
                    .addComponent(jComboBoxTipoLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLibroAnadir)
                    .addComponent(jButtonEliminarLibro)
                    .addComponent(jButtonEditarLibro))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("tab3", jPanelLibros);

        jPanelDevolucion.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Devolucion");
        jPanelDevolucion.add(jLabel4);
        jLabel4.setBounds(10, 10, 120, 50);
        jPanelDevolucion.add(jLabelFotoPrestamo1);
        jLabelFotoPrestamo1.setBounds(20, 280, 200, 100);

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\jose\\Downloads\\PROYECT_V-1.0\\PROYECT_V-1.0\\PROYECT\\src\\recurse\\Devolucion.png")); // NOI18N
        jPanelDevolucion.add(jLabel5);
        jLabel5.setBounds(90, 70, 150, 180);

        jTextFieldDevolverLib.setText("Ingrese ID libro");
        jTextFieldDevolverLib.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldDevolverLibMouseClicked(evt);
            }
        });
        jPanelDevolucion.add(jTextFieldDevolverLib);
        jTextFieldDevolverLib.setBounds(380, 130, 210, 50);

        jTextFielDevolverUsu.setText("Ingrese DNI usuario");
        jTextFielDevolverUsu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFielDevolverUsuMouseClicked(evt);
            }
        });
        jPanelDevolucion.add(jTextFielDevolverUsu);
        jTextFielDevolverUsu.setBounds(380, 60, 210, 50);

        jButtonDevolver.setText("Devolver");
        jButtonDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDevolverActionPerformed(evt);
            }
        });
        jPanelDevolucion.add(jButtonDevolver);
        jButtonDevolver.setBounds(380, 200, 210, 40);

        jTableDevolucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableDevolucion.setToolTipText("");
        jTableDevolucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDevolucionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableDevolucion);

        jPanelDevolucion.add(jScrollPane4);
        jScrollPane4.setBounds(0, 310, 660, 180);

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 2, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Tabla de los libros prestados actualmente");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelDevolucion.add(jLabel6);
        jLabel6.setBounds(0, 280, 660, 30);

        jTabbedPane.addTab("tab2", jPanelDevolucion);

        getContentPane().add(jTabbedPane);
        jTabbedPane.setBounds(0, 20, 660, 530);

        jMenu.setText("Opciones");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Inicio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Prestamos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Usuario");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Libros");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Devolucion");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu.add(jMenuItem5);

        jMenuBar1.add(jMenu);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
         jTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        jTabbedPane.setSelectedIndex(2);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButtonUsuariosAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuariosAnadirActionPerformed
        JDialogAnadirUsuario dia = new JDialogAnadirUsuario(this, rootPaneCheckingEnabled, m);
        dia.setVisible(true);
    }//GEN-LAST:event_jButtonUsuariosAnadirActionPerformed

    private void jButtonUsuariosAnadirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUsuariosAnadirMouseClicked
    }//GEN-LAST:event_jButtonUsuariosAnadirMouseClicked

    private void jComboBoxTipoBusquedaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoBusquedaUsuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoBusquedaUsuariosActionPerformed

    private void jButtonUsuariosBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuariosBuscarActionPerformed
        // TODO add your handling code here:
        String opcion = (String)jComboBoxTipoBusquedaUsuarios.getSelectedItem();
        String valor = (String)jTextFieldBuscarUsuarios.getText();
        
        if (valor.equals(""))
            preparaTablaUsuario();
        else{

            try{
                
                String titulos[] = {"NOMBRE", "APELLIDOS", "DNI", "DOMICILIO", "TELEFONO"};
                m = new DefaultTableModel(null, titulos);
                jTableUsuarios.setModel(m);
                jTableUsuarios.setDefaultEditor(Object.class, null);

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
                System.out.println("Conectado");
                String query = String.format("SELECT NOMBRE, APELLIDOS, DNI, DOMICILIO, TELEFONO FROM usuarios where %s like \"%s\";", opcion, valor+"%");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                m.setRowCount(0);
                while(rs.next()){
                    String nombre = rs.getString("NOMBRE");
                    String apellidos = rs.getString("APELLIDOS");
                    String dni = rs.getString("DNI");
                    String domicilio = rs.getString("DOMICILIO");
                    int telefono = rs.getInt("TELEFONO");

                    m.addRow(new Object[]{nombre, apellidos, dni, domicilio, telefono});
                }
                rs.close();
                stmt.close();
                con.close();
            }catch(Exception ex){
                System.out.println("No Conectado. Error:");
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jButtonUsuariosBuscarActionPerformed

    private void jTextFieldBuscarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarUsuariosActionPerformed
        
    }//GEN-LAST:event_jTextFieldBuscarUsuariosActionPerformed

    private void jTextFieldBuscarUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarUsuariosMouseClicked
        jTextFieldBuscarUsuarios.setText("");
    }//GEN-LAST:event_jTextFieldBuscarUsuariosMouseClicked

    private void jButtonEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarUsuarioActionPerformed
        
        int fila = jTableUsuarios.getSelectedRow();
        if (fila >= 0){
            String nombre = (String)m.getValueAt(fila, 0);
            m.removeRow(fila);
        
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
                System.out.println("Conectado");

                String deleteQuery = String.format("DELETE FROM usuarios WHERE NOMBRE = \"%s\";",nombre);

                PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);
                
                int rowCount = preparedStatement.executeUpdate();

                preparedStatement.close();
                con.close();
                System.out.println("Filas afectadas por la eliminacion: " + rowCount);
            }catch(Exception ex){
                System.out.println("No conectado o error al insertar los datos");
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonEliminarUsuarioActionPerformed

    private void jTextFieldBuscarLibroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarLibroMouseClicked
        jTextFieldBuscarLibro.setText("");
    }//GEN-LAST:event_jTextFieldBuscarLibroMouseClicked

    private void jTextFieldBuscarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscarLibroActionPerformed

    }//GEN-LAST:event_jTextFieldBuscarLibroActionPerformed

    private void jButtonUsuariosLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUsuariosLibroActionPerformed
        String opcion = (String)jComboBoxTipoLibro.getSelectedItem();
        String valor = (String)jTextFieldBuscarLibro.getText();
        
        if (valor.equals(""))
            preparaTablaLibro();
        else{

            try{
                
                String titulos[] = {"ID", "TITULO", "AUTOR", "TIPO", "DISPONIBLE"};
                ml = new DefaultTableModel(null, titulos);
                jTableLibro.setModel(ml);
                jTableLibro.setDefaultEditor(Object.class, null);

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
                System.out.println("Conectado");
                String query = String.format("SELECT ID, TITULO, AUTOR, TIPO, DISPONIBLE FROM libros where %s like \"%s\";", opcion, valor+"%");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ml.setRowCount(0);
                while(rs.next()){
                    String id = rs.getInt("ID")+"";
                    String titulo = rs.getString("TITULO");
                    String autor = rs.getString("AUTOR");
                    String tipo = rs.getString("TIPO");
                    boolean disponible = rs.getInt("DISPONIBLE") > 0;

                    ml.addRow(new Object[]{id, titulo, autor, tipo, disponible});
                }
                rs.close();
                stmt.close();
                con.close();
            }catch(Exception ex){
                System.out.println("No Conectado. Error:");
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonUsuariosLibroActionPerformed

    private void jButtonEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarLibroActionPerformed

        int fila = jTableLibro.getSelectedRow();
        if (fila >= 0) {
            System.out.println(fila);
            Integer id = (Integer)ml.getValueAt(fila, 0);
            ml.removeRow(fila);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "root", "");
            System.out.println("Conectado");

            String deleteQuery = "DELETE FROM libros WHERE ID = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);

            preparedStatement.setInt(1, (int)id);

            int rowCount = preparedStatement.executeUpdate();

            preparedStatement.close();
            con.close();
            System.out.println("Filas afectadas por la eliminacion: " + rowCount);
        } catch (Exception ex) {
            System.out.println("No conectado o error al eliminar los datos");
            ex.printStackTrace();
        }
        }
    }//GEN-LAST:event_jButtonEliminarLibroActionPerformed

    private void jButtonLibroAnadirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonLibroAnadirMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLibroAnadirMouseClicked

    private void jButtonLibroAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLibroAnadirActionPerformed
        JDialogAnadirLibro dia = new JDialogAnadirLibro(this, rootPaneCheckingEnabled, this);
        dia.setVisible(true);
    }//GEN-LAST:event_jButtonLibroAnadirActionPerformed

    private void jComboBoxTipoLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoLibroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoLibroActionPerformed

    private void jButtonEditarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarLibroActionPerformed
        
        int fila = jTableLibro.getSelectedRow();
        if (fila >= 0) {
            Integer id = Integer.parseInt(ml.getValueAt(fila, 0)+"");
            JDialogEditarLibro jdel = new JDialogEditarLibro(this, rootPaneCheckingEnabled, ml, id, fila);
            jdel.setVisible(true);
        }
    }//GEN-LAST:event_jButtonEditarLibroActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        jTabbedPane.setSelectedIndex(3);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButtonEditarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarUsuariosActionPerformed
        int fila = jTableUsuarios.getSelectedRow();
        if (fila >= 0) {
            String dni = (String)m.getValueAt(fila, 2);
            JDialogEditarUsuario jdel = new JDialogEditarUsuario(this, rootPaneCheckingEnabled, m, dni, fila);
            jdel.setVisible(true);
        }
    }//GEN-LAST:event_jButtonEditarUsuariosActionPerformed

    private void jButtonPrestarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrestarActionPerformed
        int fila = jTablePrestar.getSelectedRow();
        if (fila >= 0){
            int ids = Integer.parseInt(md.getValueAt(fila, 0)+"");
        
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
                System.out.println("Conectado");
                String query = String.format("SELECT l.DISPONIBLE as disponible , l.ID as id, l.TITULO as titulo, CONCAT(u.NOMBRE,' ', u.APELLIDOS) as nombre, u.DNI as dni FROM libros l JOIN usuarios u ON u.DNI = \"%s\" WHERE l.ID = %d;", jTextFielPrestarUsu.getText(), ids);
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if(rs.next()){
                    boolean disponible = rs.getInt("disponible") > 0;
                    if(disponible){
                        Integer id = rs.getInt("id");
                        String titulo = rs.getString("titulo");
                        String nombre = rs.getString("nombre");
                        String dni = rs.getString("dni");
                        con.setAutoCommit(false);
                        String updateQuery = "UPDATE libros l SET l.DISPONIBLE = 0, l.DNI_prestado = ? where l.ID = ?;";
                        PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
                        preparedStatement.setString(1, jTextFielPrestarUsu.getText());
                        preparedStatement.setInt(2, ids);
                        int rowCount = preparedStatement.executeUpdate();
                        con.commit();
                        mp.addRow(new Object[]{id, titulo, nombre, dni});
                        preparaTablasLibDisp();
                    }else
                        javax.swing.JOptionPane.showMessageDialog(this,
                            "el libro no esta disponible",
                            "Problema con la prestacion",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
                }

                rs.close();
                stmt.close();
                con.close();
            }catch(Exception ex){
                System.out.println("No Conectado. Error:");
                ex.printStackTrace();
            }
        }else
            if (md.getRowCount() > 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                            "No has seleccionado un libro, selecina uno porfavor",
                            "ID libro no indicado",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
        }else
            javax.swing.JOptionPane.showMessageDialog(this,
                            "No hay libros para prestar,\ndevulve alguno o añade alguno",
                            "No hay libros disponibles",
                            javax.swing.JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButtonPrestarActionPerformed

    private void jButtonDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDevolverActionPerformed

        String dniUsu = jTextFielDevolverUsu.getText();
        int idLib = Integer.parseInt(jTextFieldDevolverLib.getText());
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca","root","");
            System.out.println("Conectado");
            String query = String.format("SELECT l.DISPONIBLE as disponible FROM libros l WHERE l.DNI_prestado = \"%s\" and l.ID = %d;", dniUsu, idLib);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                boolean disponible = rs.getInt("disponible") > 0;
                if(!disponible){
                    con.setAutoCommit(false);
                    String updateQuery = "UPDATE libros l SET l.DISPONIBLE = 1, l.DNI_prestado = null where l.ID = ?;";
                    PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
                    preparedStatement.setInt(1, idLib);
                    preparedStatement.executeUpdate();
                    con.commit();
                    preparaTablasPrestamo();
                    preparaTablasLibDisp();
                }else
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "el libro no esta prestado",
                        "Problema con la devolucion",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            stmt.close();
            con.close();
        }catch(Exception ex){
            System.out.println("No Conectado. Error:");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonDevolverActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        jTabbedPane.setSelectedIndex(4);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jTextFielPrestarUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFielPrestarUsuMouseClicked
        jTextFielPrestarUsu.setText("");
    }//GEN-LAST:event_jTextFielPrestarUsuMouseClicked

    private void jTextFielDevolverUsuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFielDevolverUsuMouseClicked
        jTextFielDevolverUsu.setText("");
    }//GEN-LAST:event_jTextFielDevolverUsuMouseClicked

    private void jTextFieldDevolverLibMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDevolverLibMouseClicked
        jTextFieldDevolverLib.setText("");
    }//GEN-LAST:event_jTextFieldDevolverLibMouseClicked

    private void jTableDevolucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDevolucionMouseClicked
        int fila = jTableDevolucion.getSelectedRow();
        if (fila >= 0) {
            jTextFielDevolverUsu.setText(""+mp.getValueAt(fila, 3));
            jTextFieldDevolverLib.setText(""+mp.getValueAt(fila, 0));
        }
    
                
                
    }//GEN-LAST:event_jTableDevolucionMouseClicked

    private void jButtonEnseñaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnseñaUsuarioActionPerformed
        JDialogBuscarInfoUsuDev jD = new JDialogBuscarInfoUsuDev(this, rootPaneCheckingEnabled, this);
        jD.setVisible(true);
    }//GEN-LAST:event_jButtonEnseñaUsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(jFrameIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFrameIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFrameIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFrameIndex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new jFrameIndex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonDevolver;
    private javax.swing.JButton jButtonEditarLibro;
    private javax.swing.JButton jButtonEditarUsuarios;
    private javax.swing.JButton jButtonEliminarLibro;
    private javax.swing.JButton jButtonEliminarUsuario;
    private javax.swing.JButton jButtonEnseñaUsuario;
    private javax.swing.JButton jButtonLibroAnadir;
    private javax.swing.JButton jButtonPrestar;
    private javax.swing.JButton jButtonUsuariosAnadir;
    private javax.swing.JButton jButtonUsuariosBuscar;
    private javax.swing.JButton jButtonUsuariosLibro;
    private javax.swing.JComboBox<String> jComboBoxTipoBusquedaUsuarios;
    private javax.swing.JComboBox<String> jComboBoxTipoLibro;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelFechaEncabezado1;
    private javax.swing.JLabel jLabelFotoPrestamo1;
    private javax.swing.JLabel jLabelImagenInicio;
    private javax.swing.JLabel jLabelLibroTitulo;
    private javax.swing.JLabel jLabelTituloEncabezado;
    private javax.swing.JLabel jLabelUsuariosBuscar;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanelDevolucion;
    private javax.swing.JPanel jPanelEncabezado;
    private javax.swing.JPanel jPanelLibros;
    private javax.swing.JPanel jPanelPrestamos;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableDevolucion;
    private javax.swing.JTable jTableLibro;
    private javax.swing.JTable jTablePrestar;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextFielDevolverUsu;
    public javax.swing.JTextField jTextFielPrestarUsu;
    private javax.swing.JTextField jTextFieldBuscarLibro;
    private javax.swing.JTextField jTextFieldBuscarUsuarios;
    private javax.swing.JTextField jTextFieldDevolverLib;
    // End of variables declaration//GEN-END:variables
}
