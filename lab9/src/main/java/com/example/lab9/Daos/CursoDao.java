package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Usuario;
import com.example.lab9.Dto.CursoDto;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CursoDao extends DaoBase{


    public Curso obtenerCursoxId(int cursoId) {

        String sql = "SELECT * FROM lab_9.curso where idCurso = ?;";

        Curso curso = new Curso();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    curso.setIdCurso(rs.getInt(1));
                    curso.setCodigoCurso(rs.getString(2));
                    curso.setNombreCurso(rs.getString(3));
                    curso.setIdFacultad(rs.getInt(4));
                    curso.setFechaRegistro(rs.getString(5));
                    curso.setFechaEdicion(rs.getString(6));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return curso;
    }

    public int proximoIdCurso() {
        int proximoid = 1;
        int idIni = 0;

        String sql = "SELECT * FROM lab_9.curso;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                if(rs.getInt(1) == idIni + 1){
                    proximoid++;
                }
                else{
                    break;
                }
                idIni++;

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return proximoid;
    }

    public ArrayList<CursoDto> listaCursoconEvaluaciones() { // borrados
        ArrayList<CursoDto> listaCursoyEvaluaciones = new ArrayList<>();

        String sql = "SELECT c.idcurso, c.codigo, c.nombre, c.idfacultad, c.fecha_registro, c.fecha_edicion, count(e.idevaluaciones) as cantidadEvaluaciones FROM lab_9.curso c left join evaluaciones e on c.idcurso = e.idcurso group by c.idcurso;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                CursoDto curso = new CursoDto();
                curso.setIdCurso(rs.getInt(1));
                curso.setCodigoCurso(rs.getString(2));
                curso.setNombreCurso(rs.getString(3));
                curso.setIdFacultad(rs.getInt(4));
                curso.setFechaRegistro(rs.getString(5));
                curso.setFechaEdicion(rs.getString(6));
                curso.setCantidadEvaluaciones(rs.getInt(7)); //nos sirve para verificar si está o no asignado a un curso (Si es cero entonces no)

                listaCursoyEvaluaciones.add(curso);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaCursoyEvaluaciones;
    }

    public void borrarCurso(int cursoId) {
        String sql = "delete FROM lab_9.curso_has_docente where idcurso = ?;"; //Primero borramos la tabla dependiente

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);
            pstmt.executeUpdate();

            String sql1 = "delete from lab_9.curso where idcurso = ?;";
            try( PreparedStatement pstmt1 = conn.prepareStatement(sql1)){
                pstmt1.setInt(1, cursoId);
                pstmt1.executeUpdate();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void registroCurso(Curso curso){
        LocalDateTime fechayHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaReg = fechayHoraActual.format(formatoSql);

        String sql = "INSERT INTO curso (idcurso, codigo, nombre, idfacultad, fecha_registro, fecha_edicion ) VALUES (?, ?, ?, ?, ?, ?);"; //Primero agregamos a la tabla independiente

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,curso.getIdCurso());
            pstmt.setString(2,curso.getCodigoCurso());
            pstmt.setString(3, curso.getNombreCurso());
            pstmt.setInt(4, curso.getIdFacultad());
            pstmt.setString(5, fechaReg);
            pstmt.setString(6, fechaReg);

            pstmt.executeUpdate();

            String sql1 = "INSERT INTO curso_has_docente (idcurso, iddocente) VALUES (?,?);";

            try(PreparedStatement pstmt1 = conn.prepareStatement(sql1)){
                pstmt1.setInt(1,curso.getIdCurso());
                pstmt1.setInt(2,curso.getDocente().getIdUsuario());
                pstmt1.executeUpdate();
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarCurso(Curso curso) {

        LocalDateTime fechayHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaEdit = fechayHoraActual.format(formatoSql);

        String sql = "UPDATE curso SET nombre = ?, fecha_edicion = ? WHERE idcurso = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, curso.getNombreCurso());
            pstmt.setString(2,fechaEdit);
            pstmt.setInt(3,curso.getIdCurso());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }





}
