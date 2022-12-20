import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
   private static Connection getConnection(){
      Connection conn = null;
      try {
         Class.forName("org.postgresql.Driver");
         conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bootcamp_market", "postgres", "estebanco");
         //System.out.println("Opened database successfully");
      } catch (Exception e) {
         // TODO: handle exception
         System.err.println(e.getClass().getName()+": "+e.getMessage());
         System.exit(0);
      }
      return conn;
   }

   private static ResultSet executeSQL(String code, Connection conn) throws SQLException {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(code);
      return rs;
   }

   private static void printRs(ResultSet rs) throws SQLException {
      for (int k = 1; k <= rs.getMetaData().getColumnCount(); k++){
         System.out.print(rs.getMetaData().getColumnName(k).toUpperCase() + " | ");
      }
      System.out.println();

      while (rs.next()){
         for (int k = 1; k <= rs.getMetaData().getColumnCount(); k++) {
            System.out.print(rs.getString(k) + " ");
         }
         System.out.println();
      }
      System.out.println();
   }

   private static void clientesMasFacturas(Connection conn) throws SQLException {
      String query = "SELECT cliente.id cliente_id, cliente.nombre, cliente.apellido, cliente.nro_cedula, cliente.telefono, COUNT(cliente.id) cantidad_facturas FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id GROUP BY (cliente.id) ORDER BY (cantidad_facturas) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }
   
   private static void clientesMasGastaron(Connection conn) throws SQLException {
      String query = "SELECT cliente.nombre, SUM(ROUND(producto.precio*factura_detalle.cantidad)) gasto FROM cliente INNER JOIN factura ON cliente.id=factura.cliente_id INNER JOIN factura_detalle ON factura.id = factura_detalle.factura_id INNER JOIN producto ON producto.id= factura_detalle.producto_id GROUP BY (cliente.nombre) ORDER BY (gasto) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }
   
   private static void topMonedas(Connection conn) throws SQLException {
      String query = "SELECT moneda.nombre, count(moneda.nombre) cantidad_de_facturas FROM moneda INNER JOIN factura ON moneda.id=factura.moneda_id GROUP BY (moneda.nombre) ORDER BY (cantidad_de_facturas) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }
   
   private static void topProveedor(Connection conn) throws SQLException {
      String query = "SELECT proveedor.nombre, COUNT(proveedor.nombre) cantidad_de_productos FROM proveedor INNER JOIN producto ON proveedor.id = producto.proveedor_id GROUP BY(proveedor.nombre) ORDER BY (cantidad_de_productos) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }
   
   private static void masVendido(Connection conn) throws SQLException {
      String query = "SELECT producto.nombre, ROUND(SUM(factura_detalle.cantidad)) cantidad FROM producto INNER JOIN factura_detalle ON factura_detalle.producto_id = producto.id GROUP BY (producto.nombre) ORDER BY (cantidad) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }
   
   private static void menosVendido(Connection conn) throws SQLException {
      String query = "SELECT producto.nombre, ROUND(SUM(factura_detalle.cantidad)) cantidad FROM producto INNER JOIN factura_detalle ON factura_detalle.producto_id = producto.id GROUP BY (producto.nombre) ORDER BY (cantidad);";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }
   
   private static void verFacturacion(Connection conn) throws SQLException {
      String query = "SELECT f.id, f.fecha_emision, cliente.nombre, cliente.apellido, producto.nombre nombre_producto, fd.cantidad, ft.nombre FROM factura f INNER JOIN factura_detalle fd ON fd.factura_id = f.id INNER JOIN cliente ON cliente.id = f.cliente_id INNER JOIN factura_tipo ft ON ft.id = f.factura_tipo_id INNER JOIN producto ON producto.id = fd.producto_id ORDER BY f.id;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }

   private static void montoFacturacion(Connection conn) throws SQLException {
      String query = "SELECT factura.id, ROUND(SUM(factura_detalle.cantidad*producto.precio)) totales FROM factura  JOIN factura_detalle ON factura.id = factura_id  JOIN producto ON factura_detalle.producto_id = producto.id GROUP BY (factura.id) ORDER BY (totales) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }

   private static void ivaFacturacion(Connection conn) throws SQLException {
      String query = "SELECT factura.id, ROUND(SUM(factura_detalle.cantidad*producto.precio)*.11) totales FROM factura  JOIN factura_detalle ON factura.id = factura_id  JOIN producto ON factura_detalle.producto_id = producto.id GROUP BY (factura.id) ORDER BY (totales) DESC;";
      ResultSet rs = executeSQL(query, conn);
      printRs(rs);
   }

   public static void main(String args[]) throws SQLException {
      Connection conn = getConnection();
      clientesMasFacturas(conn);
      System.out.println();
      clientesMasGastaron(conn);
      System.out.println();
      topMonedas(conn);
      System.out.println();
      topProveedor(conn);
      System.out.println();
      masVendido(conn);
      System.out.println();
      menosVendido(conn);
      System.out.println();
      verFacturacion(conn);
      System.out.println();
      montoFacturacion(conn);
      System.out.println();
      ivaFacturacion(conn);
      System.out.println();
      conn.close();
   }
}
