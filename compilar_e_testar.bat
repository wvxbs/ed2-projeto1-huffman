@echo off
setlocal
cls
:: --- CABECALHO ---
echo.
echo     ==================================
echo      Script de Teste para Projeto Maven
echo     ==================================
echo.
pause

:: --- Variáveis de Caminho ---
set SRC_DIR=src\main\java
set TARGET_DIR=target
set CLASSES_DIR=%TARGET_DIR%\classes
set JAR_FILE=%TARGET_DIR%\huffman.jar
set MAIN_CLASS=com.mackenzie.Main

echo --- LIMPANDO ARQUIVOS ANTIGOS ---
:: A prática padrão do Maven é remover todo o diretório 'target'
if exist %TARGET_DIR% (
    rmdir /S /Q %TARGET_DIR%
)
:: Recria os diretórios necessários para a compilação
mkdir %CLASSES_DIR%
echo Diretorio '%TARGET_DIR%' limpo e recriado.
echo.

echo --- COMPILANDO ARQUIVOS JAVA ---
:: Cria uma lista de todos os arquivos .java para o compilador
dir /s /B "%SRC_DIR%\*.java" > sources.txt
:: Compila todos os arquivos da lista e coloca os .class no diretório de classes
javac -d %CLASSES_DIR% @sources.txt
if errorlevel 1 (
    echo.
    echo *** ERRO NA COMPILACAO! ***
    del sources.txt
    pause
    exit /b
)
del sources.txt
echo Arquivos compilados em '%CLASSES_DIR%'.
echo.

echo --- CRIANDO O ARQUIVO JAR ---
:: Cria o JAR a partir dos arquivos .class compilados
:: O comando -C muda o diretório para 'target\classes' antes de adicionar os arquivos
jar cfe %JAR_FILE% %MAIN_CLASS% -C %CLASSES_DIR% .
if errorlevel 1 (
    echo.
    echo *** ERRO AO CRIAR O JAR! ***
    pause
    exit /b
)
echo Arquivo JAR criado em '%JAR_FILE%'.
echo.

echo --- EXECUTANDO PROGRAMA ---
echo --- Comprimindo arq_de_teste.txt...
[cite_start]:: Executa o JAR que está dentro da pasta 'target' [cite: 199]
[cite_start]java -jar %JAR_FILE% -c arq_de_teste.txt teste.huff [cite: 203]

echo.
echo.
pause
echo.
echo --- Descomprimindo teste.huff...
echo.
[cite_start]java -jar %JAR_FILE% -d teste.huff teste_restaurado.txt [cite: 204]

echo.
echo --- VERIFICANDO RESULTADO ---
echo.
[cite_start]fc /B arq_de_teste.txt teste_restaurado.txt > nul [cite: 205]
if errorlevel 1 (
	[cite_start]echo *** FALHA: O arquivo original e o restaurado sao DIFERENTES. *** [cite: 205]
) else (
	[cite_start]echo *** SUCESSO: O arquivo original e o restaurado sao IDENTICOS. *** [cite: 205]
)

echo.
echo Limpando arquivos temporarios...
del teste.huff
del teste_restaurado.txt

echo.
endlocal