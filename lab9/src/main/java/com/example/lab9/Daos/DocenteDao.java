package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;
import com.example.lab9.Beans.Evaluaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DocenteDao extends DaoBase{

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


}
