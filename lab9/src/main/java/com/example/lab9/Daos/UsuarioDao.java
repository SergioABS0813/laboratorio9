package com.example.lab9.Daos;
import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Usuario;
import com.mysql.cj.protocol.a.authentication.Sha256PasswordPlugin;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UsuarioDao extends DaoBase{

    public ArrayList<Evaluaciones> listaEvaluaciones() { //Listado de todos las evaluaciones
        ArrayList<Evaluaciones> listaEvaluaciones = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.evaluaciones;";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evaluaciones evaluaciones = new Evaluaciones();
                evaluaciones.setIdEvaluaciones(rs.getInt(1));
                evaluaciones.setNombreEstudiante(rs.getString(2));
                evaluaciones.setCodigoEstudiantes(rs.getString(3));
                evaluaciones.setCorreoEstudiante(rs.getString(4));
                evaluaciones.setNota(rs.getInt(5));
                evaluaciones.setIdSemetre(rs.getInt(7)); // Luego pasara a ser el nombre

                listaEvaluaciones.add(evaluaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluaciones;
    }

    public ArrayList<Curso> listaCursos() { //Listado de todos los cursos
        ArrayList<Curso> listaCursos = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.curso;";
        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt(1));
                curso.setCodigoCurso(rs.getString(2));
                curso.setNombreCurso(rs.getString(3));
                curso.setFechaRegistro(rs.getString(5));
                curso.setFechaEdicion(rs.getString(6));

                listaCursos.add(curso);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaCursos;
    }

    public ArrayList<Usuario> listaDocentesTotal() { //vista decano
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.usuario where idrol = 4;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario docente = new Usuario();
                docente.setIdUsuario(rs.getInt(1));
                docente.setNombre(rs.getString(2));
                docente.setCorreo(rs.getString(3));
                docente.setUltimoIngreso(rs.getString(6));
                docente.setCantidadIngresos(rs.getInt(7));
                docente.setFechaRegistro(rs.getString(8));
                docente.setFechaEdicion(rs.getString(9));

                listaDocentes.add(docente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }


    public ArrayList<Usuario> listaDocentesDisponibles() { //Combobox y borrados
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.usuario u\n" +
                "left join curso_has_docente c on u.idusuario = c.iddocente where c.idcurso is null and u.idrol = 4;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario docentedisponible = new Usuario();
                docentedisponible.setIdUsuario(rs.getInt(1));
                docentedisponible.setNombre(rs.getString(2));
                docentedisponible.setCorreo(rs.getString(3));
                docentedisponible.setFechaRegistro(rs.getString(8));

                listaDocentes.add(docentedisponible);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }

    public int proximoIdUsuario() {
        int proximoid = 0;
        int idIni = 0;

        String sql = "SELECT * FROM lab_9.usuario;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if(rs.getInt(1) == idIni + 1){
                    proximoid++;
                }else{
                    break;
                }
                idIni++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return proximoid;
    }

    public Usuario obtenerUsuarioxId(int userId) {

        String sql = "SELECT * FROM lab_9.usuario where idusuario = ?;";

        Usuario usuario = new Usuario();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setCorreo(rs.getString(3));
                    usuario.setUltimoIngreso(rs.getString(6));
                    usuario.setCantidadIngresos(rs.getInt(7));
                    usuario.setFechaEdicion(rs.getString(9));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }


    public ArrayList<Usuario> listaDocentesSinCurso() { // borrados
        ArrayList<Usuario> listaDocentes = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.usuario u left join curso_has_docente c on u.idusuario = c.iddocente where u.idrol = 4;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario docentedisponible = new Usuario();
                docentedisponible.setIdUsuario(rs.getInt(1));
                docentedisponible.setNombre(rs.getString(2));
                docentedisponible.setCorreo(rs.getString(3));
                docentedisponible.setUltimoIngreso(rs.getString(6));
                docentedisponible.setCantidadIngresos(rs.getInt(7));
                docentedisponible.setFechaRegistro(rs.getString(8));
                docentedisponible.setFechaEdicion(rs.getString(9));
                Curso curso = new Curso();
                curso.setIdCurso(rs.getInt(10)); // Si es null, luego se convertirá en cero, nos sirve para verificar si está o no asignado a un curso

                docentedisponible.setCurso(curso);

                listaDocentes.add(docentedisponible);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaDocentes;
    }

    public void borrarDocente(int docenteId) {

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM usuario WHERE idusuario = ?")) {

            pstmt.setInt(1, docenteId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Usuario obtenerUsuarioxNombre(String nombre) {

        String sql = "SELECT * FROM lab_9.usuario where nombre = ?;";

        Usuario usuario = new Usuario();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setCorreo(rs.getString(3));
                    usuario.setUltimoIngreso(rs.getString(6));
                    usuario.setCantidadIngresos(rs.getInt(7));
                    usuario.setFechaEdicion(rs.getString(9));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    public void registrarDocente(Usuario docente) {

        LocalDateTime fechayHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaRegistro = fechayHoraActual.format(formatoSql);

        int cantIngresos = 0;
        int idrol = 4;

        String sql = "INSERT INTO usuario (idusuario, nombre, correo, password, idrol, ultimo_ingreso, cantidad_ingresos, fecha_registro, fecha_edicion) "
                + "VALUES (?, ?, ?, sha2(?,256), ?, ?, ?, ?, ?)";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,docente.getIdUsuario());
            pstmt.setString(2,docente.getNombre());
            pstmt.setString(3, docente.getCorreo());
            pstmt.setString(4, docente.getPassword());
            pstmt.setInt(5,idrol);
            pstmt.setString(6,fechaRegistro);
            pstmt.setInt(7,cantIngresos);
            pstmt.setString(8,fechaRegistro);
            pstmt.setString(9,fechaRegistro);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarDocente(Usuario docente) {

        LocalDateTime fechayHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaEdit = fechayHoraActual.format(formatoSql);

        String sql = "UPDATE usuario SET nombre = ?, fecha_edicion = ? WHERE idusuario = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2,fechaEdit);
            pstmt.setInt(3,docente.getIdUsuario());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean validarUsuarioPasswordHashed(String correo, String password){

        String sql = "SELECT * FROM usuario where correo = ? and password = sha2(?,256)";

        boolean exito = false;

        try(Connection connection = getConection();
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,correo);
            pstmt.setString(2,password);

            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    exito = true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return exito;
    }

    public Usuario obtenerUsuarioxCorreo(String correo) {

        String sql = "SELECT * FROM lab_9.usuario where correo = ?;";

        Usuario usuario = new Usuario();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setCorreo(rs.getString(3));
                    usuario.setIdRol(rs.getInt(5));
                    usuario.setUltimoIngreso(rs.getString(6));
                    usuario.setCantidadIngresos(rs.getInt(7));
                    usuario.setFechaEdicion(rs.getString(9));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }




















}
