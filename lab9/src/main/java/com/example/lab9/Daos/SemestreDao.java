package com.example.lab9.Daos;

import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Semestre;
import com.example.lab9.Dto.CursoDto;

import java.sql.*;
import java.util.ArrayList;

public class SemestreDao extends DaoBase {

    public ArrayList<Semestre> listaSemestre() { // borrados
        ArrayList<Semestre> listaSemestre = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.semestre";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Semestre semestre = new Semestre();
                semestre.setIdSemestre(rs.getInt(1));
                semestre.setNombre(rs.getString(2));
                semestre.setHabilitado(rs.getInt(3));

                listaSemestre.add(semestre);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaSemestre;
    }



    public ArrayList<Evaluaciones> listaEvaluacionConSemestre() { // borrados
        ArrayList<Evaluaciones> listaEvaluacionConSemestre = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.evaluaciones e left join semestre s on e.idsemestre = s.idsemestre;";

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
                evaluaciones.setIdCurso(rs.getInt(6));

                Semestre semestre = new Semestre();
                semestre.setIdSemestre(rs.getInt(7));
                semestre.setNombre(rs.getString(11));
                semestre.setHabilitado(rs.getInt(13));
                evaluaciones.setSemestre(semestre); //nos sirve para verificar si está o no asignado a un curso (Si es cero entonces no habilitado)

                listaEvaluacionConSemestre.add(evaluaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluacionConSemestre;
    }


    public ArrayList<Evaluaciones> listaEvaluacionConSemestreHabilitados() { // borrados
        ArrayList<Evaluaciones> listaEvaluacionConSemestre = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.evaluaciones e left join semestre s on e.idsemestre = s.idsemestre where habilitado = 1;";

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
                evaluaciones.setIdCurso(rs.getInt(6));

                Semestre semestre = new Semestre();
                semestre.setIdSemestre(rs.getInt(7));
                semestre.setNombre(rs.getString(11));
                semestre.setHabilitado(rs.getInt(13));
                evaluaciones.setSemestre(semestre); //nos sirve para verificar si está o no asignado a un curso (Si es cero entonces no habilitado)

                listaEvaluacionConSemestre.add(evaluaciones);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluacionConSemestre;
    }

    public Semestre buscarSemestreHabilitado() { // borrados
        Semestre semestre = new Semestre();

        String sql = "SELECT * FROM lab_9.evaluaciones e left join semestre s on e.idsemestre = s.idsemestre where habilitado = 1;";

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if(rs.next()){
                semestre.setHabilitado(rs.getInt(1));
                semestre.setNombre(rs.getString(2));
                semestre.setIdSemestre(rs.getInt(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return semestre;
    }
    public ArrayList<Evaluaciones> listaEvaluacionConSemestreFinal(int idCursoDoc) {
        ArrayList<Evaluaciones> listaEvaluacionConSemestre = new ArrayList<>();

        String sql = "SELECT * FROM lab_9.evaluaciones e left join semestre s on e.idsemestre = s.idsemestre where e.idcurso = ?;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, idCursoDoc);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Evaluaciones evaluaciones = new Evaluaciones();
                    evaluaciones.setIdEvaluaciones(rs.getInt(1));
                    evaluaciones.setNombreEstudiante(rs.getString(2));
                    evaluaciones.setCodigoEstudiantes(rs.getString(3));
                    evaluaciones.setCorreoEstudiante(rs.getString(4));
                    evaluaciones.setNota(rs.getInt(5));
                    evaluaciones.setIdCurso(rs.getInt(6));
                    evaluaciones.setFechaRegistro(rs.getString(8));
                    evaluaciones.setFechaEdicion(rs.getString(9));

                    Semestre semestre = new Semestre();
                    semestre.setIdSemestre(rs.getInt(7));
                    semestre.setNombre(rs.getString(11));
                    semestre.setHabilitado(rs.getInt(13));
                    evaluaciones.setSemestre(semestre); //nos sirve para verificar si está o no asignado a un curso (Si es cero entonces no habilitado)

                    listaEvaluacionConSemestre.add(evaluaciones);
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEvaluacionConSemestre;
    }

    public Semestre buscarSemestrexNombre(String nombre) {
        Semestre semestre = new Semestre();

        String sql = "SELECT * FROM lab_9.semestre where nombre = ?;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, nombre);

            try (ResultSet rs = pstmt.executeQuery()) {


                if (rs.next()) {
                    semestre.setIdSemestre(rs.getInt(1));
                    semestre.setNombre(rs.getString(2));
                }
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return semestre;
    }






}
