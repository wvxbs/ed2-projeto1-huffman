#!/bin/bash
clear

# --- Variáveis de Caminho para a estrutura Maven ---
# Define os caminhos para facilitar a manutenção
SRC_DIR="src/main/java"
TARGET_DIR="target"
CLASSES_DIR="${TARGET_DIR}/classes"
JAR_FILE="${TARGET_DIR}/huffman.jar"
MAIN_CLASS="com.mackenzie.Main"


# --- CABECALHO ---
echo ""
echo "    =================================="
echo "      Script de Teste para Projeto Maven"
echo "    =================================="
echo ""
read -p "Pressione Enter para continuar..."


# --- ETAPA DE LIMPEZA ---
echo "--- LIMPANDO ARQUIVOS ANTIGOS ---"
# A prática padrão é remover todo o diretório 'target'
rm -rf "$TARGET_DIR"
# Recria os diretórios necessários para a compilação
mkdir -p "$CLASSES_DIR"
echo "Diretório '$TARGET_DIR' limpo e recriado."


# --- ETAPA DE COMPILAÇÃO ---
echo ""
echo "--- COMPILANDO ARQUIVOS JAVA ---"
# Encontra todos os arquivos .java no diretório de fontes
# e compila, colocando os .class no diretório de classes
find "$SRC_DIR" -name "*.java" -print | xargs javac -d "$CLASSES_DIR"
if [ $? -ne 0 ]; then
    echo ""
    echo "*** ERRO NA COMPILACAO! ***"
    exit 1
fi
echo "Arquivos compilados em '$CLASSES_DIR'."


# --- ETAPA DE CRIAÇÃO DO JAR ---
echo ""
echo "--- CRIANDO O ARQUIVO JAR ---"
# Cria o JAR a partir dos arquivos .class compilados
# O comando -C muda o diretório para 'target/classes' antes de adicionar os arquivos
jar cfe "$JAR_FILE" "$MAIN_CLASS" -C "$CLASSES_DIR" .
if [ $? -ne 0 ]; then
    echo ""
    echo "*** ERRO AO CRIAR O JAR! ***"
    exit 1
fi
echo "Arquivo JAR criado em '$JAR_FILE'."


# --- ETAPA DE EXECUÇÃO E TESTE ---
echo ""
echo "--- EXECUTANDO PROGRAMA ---"
echo "--- Comprimindo arq_de_teste.txt..."
# Executa o JAR que está dentro da pasta 'target'
java -jar "$JAR_FILE" -c arq_de_teste.txt teste.huff

echo ""
read -p "Pressione Enter para continuar..."
echo ""
echo "--- Descomprimindo teste.huff..."
echo ""
java -jar "$JAR_FILE" -d teste.huff teste_restaurado.txt

echo ""
echo "--- VERIFICANDO RESULTADO ---"
echo ""
cmp -s arq_de_teste.txt teste_restaurado.txt
if [ $? -ne 0 ]; then
	echo "*** FALHA: O arquivo original e o restaurado sao DIFERENTES. ***"
else
	echo "*** SUCESSO: O arquivo original e o restaurado sao IDENTICOS. ***"
fi


# --- LIMPEZA FINAL ---
echo ""
echo "Limpando arquivos temporarios..."
rm -f teste.huff
rm -f teste_restaurado.txt

echo ""