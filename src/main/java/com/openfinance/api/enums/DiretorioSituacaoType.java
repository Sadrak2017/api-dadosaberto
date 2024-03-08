package com.openfinance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DiretorioSituacaoType {
  EXCLUSAO_LOGICA(99, "Usuário excluído apenas logicamente"),
  ATIVO(1, "Usuário ativo"),
  BLOQUEIO_SENHA(2, "Bloqueado por digitar senha inválida 4 vezes"),
  BLOQUEIO_ADMIN(3, "Bloqueado por violação dos termos"),
  PENDENTE_VALIDACAO_PIN(4, "Pendente de validação");

  public final int code;
  public final String desc;

  public static DiretorioSituacaoType getType(int code){
    for(final DiretorioSituacaoType diretorioSituacaoType : values()){
      if (diretorioSituacaoType.code == code)
        return diretorioSituacaoType;
    }
    return null;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
