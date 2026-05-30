package br.senac.tads.dsw.medicoscrud.service;

import br.senac.tads.dsw.medicoscrud.dto.MedicoDto;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
	List<MedicoDto> obterMedicos();
	Optional<MedicoDto> obterMedico(Long id);
	MedicoDto incluirNovoMedico(MedicoDto medico);
	MedicoDto alterarMedico(Long id, MedicoDto medicoDto);
	void removerMedico(Long id);
}

