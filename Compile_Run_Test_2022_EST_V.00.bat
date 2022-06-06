@echo off

REM === Mensaje bienvenida
echo Se va a compilar la practica 2021/2022 con las dependencias y restricciones


REM === Inputs ===
set SRC_DIR=%cd%\src
set BIN_DIR=\bin
set MAIN=es/uned/lsi/eped/pract2021_2022/Main
set JAVA_HOME_JDK=""
set TMP_FOLDER=%cd%\juego_de_pruebas_2022\tmp

IF %JAVA_HOME_JDK%=="" (

	IF "%JAVA_HOME%" == "" (
	    echo Modifica la variable del archivo bat JAVA_HOME_JDK 
	    pause
	    exit
	) ELSE (
	    set JAVA_HOME_JDK="%JAVA_HOME%"
	)
)


REM === Mostramos variables ===
echo Carpeta codigo = %SRC_DIR%
echo Carpeta bin = %BIN_DIR%
echo Clase principal = %MAIN%
echo JAVA_HOME_JDK = %JAVA_HOME_JDK%
echo. 
echo.


REM === Clean and make temp dir ===
echo Limpiando compilacion anterior 
rd /q /s "%TMP_FOLDER%"
pause
if not exist "%TMP_FOLDER%" mkdir "%TMP_FOLDER%" 
mkdir "%TMP_FOLDER%%BIN_DIR%"
mkdir "%TMP_FOLDER%\src"
mkdir "%TMP_FOLDER%\src\es"
mkdir "%TMP_FOLDER%\src\es\uned"
mkdir "%TMP_FOLDER%\src\es\uned\lsi"
mkdir "%TMP_FOLDER%\src\es\uned\lsi\eped"
mkdir "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2021_2022"
xcopy /s/q "%SRC_DIR%\es\uned\lsi\eped\pract2021_2022" "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2021_2022"
xcopy /y/q "juego_de_pruebas_2022\lib\src\Main.java" "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2021_2022"
xcopy /y/q "juego_de_pruebas_2022\lib\src\SparseArrayBTreeChecker.java" "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2021_2022"
echo.
echo.
pause


REM ===

REM ===========================================
REM === Comprobacion uso TAD equipo docente ===
REM ===========================================
echo Comprobando el uso de estructuras de datos del equipo docente
cd "%TMP_FOLDER%\src\es\uned\lsi\eped\pract2021_2022"
find "import" SparseArray* | find /v "es.uned.lsi.eped.DataStructures"
cd "../../../../../../../../"
echo Si se muestra algun "import" en el mensaje anterior es posible que no se este haciendo uso de las estructuras de datos del equipo docente
echo. 
echo.
pause


REM ===

REM ===============
REM === Compile ===
REM ===============
echo Compilando en carpeta temporal
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\javac" -d "%TMP_FOLDER%%BIN_DIR%" -sourcepath "%TMP_FOLDER%\src" -cp "juego_de_pruebas_2022/lib/TAD_modified.jar" "%TMP_FOLDER%\src\%MAIN%.java"

%JAVA_HOME_JDK%"\bin\javac" -d "%TMP_FOLDER%%BIN_DIR%" -sourcepath "%TMP_FOLDER%\src" -cp "juego_de_pruebas_2022/lib/TAD_modified.jar" "%TMP_FOLDER%\src\%MAIN%.java"
if errorlevel 1 (
	echo Compilacion con errores
	pause
	exit /B 1
)

echo Compilacion sin errores
echo.
echo.
pause

REM ===

REM ===========================================
REM === Comprobacion basica eliminacion nodos ===
REM ===========================================
REM echo Prueba basica eliminacion nodos
REM REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar;juego_de_pruebas_2022/lib/BasicTest.jar" es.uned.lsi.eped.pract2021_2022.MainBateriaPruebas

REM %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar;juego_de_pruebas_2022/lib/BasicTest.jar" es.uned.lsi.eped.pract2021_2022.MainBateriaPruebas

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )
REM echo. 
REM echo.
REM pause



REM ==============================================================================

REM =============================
REM === Run SEQUENCE Estudiantes Basica 2 ===
REM =============================
echo Ejecutando el programa con secuencia basica
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat"

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo. 
echo.
pause


REM === Comprobacion Estudiantes Basica 2 ===
echo Comprobando bateria de pruebas para secuencia basica
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB1.txt"

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB1.txt"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)
echo. 
echo.
pause


REM ==============================================================================

REM =============================
REM === Run BTREE Estudiantes Basica 2 ===
REM =============================
echo Ejecutando el programa con arbol binario de busqueda basica
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat"

REM %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )

REM echo Ejecucion sin errores
REM echo. 
REM echo.
REM pause

REM === Comprobacion Estudiantes Basica 2 ===
echo Comprobando bateria de pruebas para arbol binario de busqueda basica
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB2.txt"

REM %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB2.txt"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )
REM echo. 
REM echo.
REM pause

REM =========================================


REM ==============================================================================

REM =============================
REM === Run SEQUENCE Estudiantes PP ===
REM =============================
echo Ejecutando el programa con secuencia (pocas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat"

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo. 
echo.
pause


REM === Comprobacion Estudiantes PP ===
echo Comprobando bateria de pruebas para secuencia (pocas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP1.txt"

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP1.txt"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)
echo. 
echo.
pause


REM ==============================================================================

REM =============================
REM === Run BTREE Estudiantes PP ===
REM =============================
echo Ejecutando el programa con arbol binario de busqueda (pocas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat"

REM %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )

REM echo Ejecucion sin errores
REM echo. 
REM echo.
REM pause

REM === Comprobacion Estudiantes PP ===
echo Comprobando bateria de pruebas para arbol binario de busqueda (pocas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP2.txt"

REM %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP2.txt"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )
REM echo. 
REM echo.
REM pause


REM ==============================================================================

REM =============================
REM === Run SEQUENCE Estudiantes MP ===
REM =============================
echo Ejecutando el programa con secuencia (muchas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat"

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo. 
echo.
pause


REM === Comprobacion Estudiantes MP ===
echo Comprobando bateria de pruebas para secuencia (muchas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP1.txt"

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP1.txt"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)
echo. 
echo.
pause


REM ==============================================================================

REM =============================
REM === Run BTREE Estudiantes MP ===
REM =============================
echo Ejecutando el programa con arbol binario de busqueda (muchas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat"

REM %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )

REM echo Ejecucion sin errores
REM echo. 
REM echo.
REM pause

REM === Comprobacion Estudiantes MP ===
echo Comprobando bateria de pruebas para arbol binario de busqueda (muchas instrucciones y pocos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP2.txt"

REM %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP2.txt"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )
REM echo. 
REM echo.
REM pause

REM =========================================


REM ==============================================================================

REM =============================
REM === Run SEQUENCE Estudiantes MM ===
REM =============================
echo Ejecutando el programa con secuencia (muchas instrucciones y muchos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat"

%JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)

echo Ejecucion sin errores
echo. 
echo.
pause


REM === Comprobacion Estudiantes MM ===
echo Comprobando bateria de pruebas para secuencia (muchas instrucciones y muchos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM1.txt"

%JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM1.txt"

if errorlevel 1 (
	echo Ejecucion con errores
	pause
	exit /B 1
)
echo. 
echo.
pause


REM ==============================================================================

REM =============================
REM === Run BTREE Estudiantes MM ===
REM =============================
echo Ejecutando el programa con arbol binario de busqueda (muchas instrucciones y muchos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat"

REM %JAVA_HOME_JDK%"\bin\java" -cp "%TMP_FOLDER%%BIN_DIR%;juego_de_pruebas_2022/lib/TAD_modified.jar" "%MAIN%" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )

REM echo Ejecucion sin errores
REM echo. 
REM echo.
REM pause

REM === Comprobacion Estudiantes MM ===
echo Comprobando bateria de pruebas para arbol binario de busqueda (muchas instrucciones y muchos indices)
REM === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
REM echo %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM2.txt"

REM %JAVA_HOME_JDK%"\bin\java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM2.txt"

REM if errorlevel 1 (
	REM echo Ejecucion con errores
	REM pause
	REM exit /B 1
REM )
REM echo. 
REM echo.
REM pause

REM =========================================


