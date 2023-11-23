package com.example.lab9.Daos;

import com.example.lab9.Beans.Curso;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DecanoDao extends DaoBase {


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



}
