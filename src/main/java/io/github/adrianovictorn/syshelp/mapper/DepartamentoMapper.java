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

        setorParaDepartamento = Collections.unmodifiableMap(map);
    }

    public static Departamento getDepartamento(Setores setor) {
        return setorParaDepartamento.get(setor);
    }

    public static boolean isSetorDoDepartamento(Setores setor, Departamento departamento) {
        return getDepartamento(setor) == departamento;
    }
}


    
