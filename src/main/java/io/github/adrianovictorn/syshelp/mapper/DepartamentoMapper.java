package io.github.adrianovictorn.syshelp.mapper;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import io.github.adrianovictorn.syshelp.entity.Enums.Departamento;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;

public class DepartamentoMapper {

    private static final Map<Setores, Departamento> setorParaDepartamento;

    static{
        Map<Setores, Departamento> map = new EnumMap<>(Setores.class);

        //Prefeitura
        map.put(Setores.CONVENIOS, Departamento.PM);
        map.put(Setores.COMUNICACAO, Departamento.PM);
        map.put(Setores.CONTABILIDADE, Departamento.PM);
        map.put(Setores.CONTROLE_INTERNO, Departamento.PM);
        map.put(Setores.CONVENIOS, Departamento.PM);
        map.put(Setores.GABINETE, Departamento.PM);
        map.put(Setores.LICITACAO, Departamento.PM);
        map.put(Setores.RECEPCAO, Departamento.PM);
        map.put(Setores.RH, Departamento.PM);
        map.put(Setores.TESOURARIA, Departamento.PM);
        map.put(Setores.TRIBUTOS, Departamento.PM);

        //SMS
        map.put(Setores.AB, Departamento.SMS);
        map.put(Setores.FARMACIA, Departamento.SMS);
        map.put(Setores.ALMOXARIFADO, Departamento.SMS);
        map.put(Setores.RECEPCAO, Departamento.SMS);
        map.put(Setores.CPD, Departamento.SMS);
        map.put(Setores.VIEP, Departamento.SMS);
        map.put(Setores.VISA,Departamento.SMS);


        //Hospital
        map.put(Setores.CONSULTORIO_M,Departamento.HMCA);
        map.put(Setores.FARMACIA,Departamento.HMCA);
        map.put(Setores.RAIO_X,Departamento.HMCA);

        setorParaDepartamento = Collections.unmodifiableMap(map);
    }

    public static Departamento getDepartamento(Setores setor) {
        return setorParaDepartamento.get(setor);
    }

    public static boolean isSetorDoDepartamento(Setores setor, Departamento departamento) {
        return getDepartamento(setor) == departamento;
    }
}


    
