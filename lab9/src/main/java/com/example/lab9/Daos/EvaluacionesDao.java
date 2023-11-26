package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EvaluacionesDao extends DaoBase{

    public Evaluaciones obtenerEvaxId(int cursoId) {

        String sql = "SELECT * FROM lab_9.evaluaciones where idevaluaciones = ?;";

        Evaluaciones evaluaciones = new Evaluaciones();

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cursoId);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    evaluaciones.setIdEvaluaciones(rs.getInt(1));
                    evaluaciones.setNombreEstudiante(rs.getString(2));
                    evaluaciones.setCodigoEstudiantes(rs.getString(3));
                    evaluaciones.setCorreoEstudiante(rs.getString(4));
                    evaluaciones.setNota(rs.getInt(5));
                    evaluaciones.setIdSemetre(rs.getInt(7));
                    evaluaciones.setFechaRegistro(rs.getString(8));
                    evaluaciones.setFechaEdicion(rs.getString(9));

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return evaluaciones;
    }

    public int proximoIdEvaluacion() {
        int proximoid = 1;
        int idIni = 0;

        String sql = "SELECT * FROM lab_9.evaluaciones;";

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

    public void borrarEvaluacion(int evaId) {

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM evaluaciones WHERE idevaluaciones = ?")) {

            pstmt.setInt(1, evaId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void registrarEvaluacion(Evaluaciones evaluacion){
        LocalDateTime fechayHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaReg = fechayHoraActual.format(formatoSql);

        String sql = "INSERT INTO evaluaciones (idevaluaciones, nombre_estudiantes, codigo_estudiantes, correo_estudiantes, nota, idcurso, idsemestre, fecha_registro, fecha_edicion ) VALUES (?, ?, ?, ?, ?, ?,?,?,?);"; //Primero agregamos a la tabla independiente

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,evaluacion.getIdEvaluaciones());
            pstmt.setString(2,evaluacion.getNombreEstudiante());
            pstmt.setString(3, evaluacion.getCodigoEstudiantes());
            pstmt.setString(4, evaluacion.getCorreoEstudiante());
            pstmt.setInt(5, evaluacion.getNota());
            pstmt.setInt(6, evaluacion.getIdCurso());
            pstmt.setInt(7,evaluacion.getSemestre().getIdSemestre());
            pstmt.setString(8,fechaReg);
            pstmt.setString(9,fechaReg);

            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarEva(int idEva, int notaEva) {

        LocalDateTime fechayHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaEdit = fechayHoraActual.format(formatoSql);

        String sql = "UPDATE evaluaciones SET nota = ?, fecha_edicion = ? WHERE idevaluaciones = ?";

        try (Connection conn = getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, notaEva);
            pstmt.setString(2,fechaEdit);
            pstmt.setInt(3,idEva);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
