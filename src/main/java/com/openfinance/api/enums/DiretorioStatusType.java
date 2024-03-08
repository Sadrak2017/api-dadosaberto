package com.openfinance.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiretorioStatusType {
    ERRO_INESPERADO(0, "Erro ao tentar acessar dados"),
    DADOS_RECUPERADO(1, "Dados extraídos do diretório com sucesso!"),
    BLOQUEIO_BANIMENTO(2, "Bloqueado por violação dos termos"),
    BLOQUEIO_SENHA(3, "Bloqueado por digitar senha inválida 4 vezes"),
    USUARIO_INEXISTENTE(4, "O usuário informado não consta na base."),
    SENHA_INVALIDA(5, "Usuário ou senha inválido!"),
    USUARIO_JA_EXISTE(6, "Usuário já consta na base com este e-mail. Recupere a conta ou faça login!"),
    USUARIO_EXCLUIDO(7, "Usuário removido com sucesso!"),
    USUARIO_CADASTRADO(8, "Cadastro realizado!"),
    BLOQUEIO_BANIMENTO_REMOVIDO(9, "Bloqueado removido. Usuário ativado novamente!"),
    PIN_ENVIADO_SUCESSO(10, "Código PIN enviado com sucesso!"),
    PIN_ERRO_ENVIO(11, "Falha ao enviar código PIN"),
    PIN_VALIDO_SUCESSO(12, "Código PIN validado com sucesso!"),
    PIN_INVALIDO(13, "Código PIN inválido!"),
    USUARIO_ATAULIZADO(14, "Cadastro atualizado com sucesso!"),
    USUARIO_JA_EXISTE_DOC(15, "Usuário já consta na base com o documento informado. Recupere a conta ou faça login!"),
    USUARIO_STATUS(16, "Status atualizado com sucesso!"),
    TERMOS_ACEITO(16, "Termos atualizado com sucesso!"),
    USUARIO_SNHA(16, "Senha atualizado com sucesso!");;

    public final int code;
    public final String desc;

    public static DiretorioStatusType getType(int code){
        for(final DiretorioStatusType diretorioStatusType : values()){
            if (diretorioStatusType.code == code)
                return diretorioStatusType;
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
