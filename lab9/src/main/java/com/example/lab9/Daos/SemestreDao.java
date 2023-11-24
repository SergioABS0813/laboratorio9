package com.example.lab9.Daos;

import com.example.lab9.Beans.Evaluaciones;
import com.example.lab9.Beans.Semestre;
import com.example.lab9.Dto.CursoDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SemestreDao extends DaoBase {



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

}
