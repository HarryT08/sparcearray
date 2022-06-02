
#!/bin/bash
# === Mensaje bienvenida
echo Se va a compilar la practica 2021\/2022 con las dependencias y restricciones


# === Inputs ===
SRC_DIR=$PWD/src
BIN_DIR=/bin
MAIN=es/uned/lsi/eped/pract2021_2022/Main
JAVA_HOME_JDK=/usr/lib/jvm/default-java
TMP_FOLDER=$PWD/juego_de_pruebas_2022/tmp

if [ -z "$JAVA_HOME_JDK" ] 
    then
        if [ -z "$JAVA_HOME" ] 
            then 
	        echo Modifica la variable del archivo .sh JAVA_HOME_JDK 
	        read -rsp $'Press any key to continue...\n' -n 1 key
	        exit
	 else
	    JAVA_HOME_JDK="$JAVA_HOME"
	 fi
fi


# === Mostramos variables ===
echo Carpeta codigo = $SRC_DIR
echo Carpeta bin = $BIN_DIR
echo Clase principal = $MAIN
echo JAVA_HOME_JDK = $JAVA_HOME_JDK
echo  
echo 


# === Clean and make temp dir ===
echo Limpiando compilacion anterior
rm -rf "$TMP_FOLDER"
read -rsp $'Press any key to continue...\n' -n 1 key
echo Copiando archivos
if [ ! -e "$TMP_FOLDER" ]
    then
        mkdir "$TMP_FOLDER" 
fi 
mkdir "$TMP_FOLDER$BIN_DIR"
mkdir "$TMP_FOLDER/src"
mkdir "$TMP_FOLDER/src/es"
mkdir "$TMP_FOLDER/src/es/uned"
mkdir "$TMP_FOLDER/src/es/uned/lsi"
mkdir "$TMP_FOLDER/src/es/uned/lsi/eped"
mkdir "$TMP_FOLDER/src/es/uned/lsi/eped/pract2021_2022"
cp -r "$SRC_DIR/es/uned/lsi/eped/pract2021_2022/"* "$TMP_FOLDER/src/es/uned/lsi/eped/pract2021_2022"
cp -v "juego_de_pruebas_2022/lib/src/"* "$TMP_FOLDER/src/es/uned/lsi/eped/pract2021_2022"
echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ===========================================
# === Comprobacion uso TAD equipo docente ===
# ===========================================
echo Comprobando el uso de estructuras de datos del equipo docente
cd "$TMP_FOLDER/src/es/uned/lsi/eped/pract2021_2022"
grep "import" *SparseArray* | grep -v "es.uned.lsi.eped.DataStructures"
cd "../../../../../../../../"
echo Si se muestra algun \"import\" en el mensaje anterior es posible que no se este haciendo uso de las estructuras de datos del equipo docente
echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# =============================
# === Compile ===
# =============================
echo Compilando en carpeta temporal
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/javac" -d "$TMP_FOLDER$BIN_DIR" -sourcepath "$TMP_FOLDER/src" -cp "juego_de_pruebas_2022/lib/TAD_modified.jar" "$TMP_FOLDER/src/"$MAIN".java"

"$JAVA_HOME_JDK/bin/javac" -d "$TMP_FOLDER$BIN_DIR" -sourcepath "$TMP_FOLDER/src" -cp "juego_de_pruebas_2022/lib/TAD_modified.jar" "$TMP_FOLDER/src/"$MAIN".java"

if [ $? -eq 1 ] 
    then
	echo Compilacion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Compilacion sin errores
echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# =============================
# === Comprobacion basica eliminacion nodos ===
# =============================
echo Prueba basica eliminacion nodos
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar:juego_de_pruebas_2022/lib/BasicTest.jar"  es.uned.lsi.eped.pract2021_2022.MainBateriaPruebas

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar:juego_de_pruebas_2022/lib/BasicTest.jar"  es.uned.lsi.eped.pract2021_2022.MainBateriaPruebas

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# =============================
# === Run SEQUENCE Estudiantes Basica 2 ===
# =============================
echo Ejecutando el programa con secuencia basica
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes MP ===
echo Comprobando bateria de pruebas para secuencia basica
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB1.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB1.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# ==============================
# === Run BTREE Estudiantes Basica 2 ===
# ==============================
echo Ejecutando el programa con arbol binario de busqueda basica
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_18_Basic_2.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes MM ===
echo Comprobando bateria de pruebas para arbol binario de busqueda basica
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB2.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_18_Basic_2_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_18_Basic_2.dat" "erroresB2.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===



# ==========================================================================================
# =============================
# === Run SEQUENCE Estudiantes PP ===
# =============================
echo Ejecutando el programa con secuencia \(pocas instrucciones y pocos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes PP ===
echo Comprobando bateria de pruebas para secuencia \(pocas instrucciones y pocos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP1.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP1.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# ==============================
# === Run BTREE Estudiantes PP ===
# ==============================
echo Ejecutando el programa con arbol binario de busqueda \(pocas instrucciones y pocos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_1000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes PP ===
echo Comprobando bateria de pruebas para arbol binario de busqueda \(pocas instrucciones y pocos indice\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP2.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_1000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_1000_100.dat" "erroresPP2.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===



# ==========================================================================================
# =============================
# === Run SEQUENCE Estudiantes MP ===
# =============================
echo Ejecutando el programa con secuencia \(muchas instrucciones y pocos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes MP ===
echo Comprobando bateria de pruebas para secuencia \(muchas instrucciones y pocos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP1.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP1.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# ==============================
# === Run BTREE Estudiantes MP ===
# ==============================
echo Ejecutando el programa con arbol binario de busqueda \(muchas instrucciones y pocos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_15000_100.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes MP ===
echo Comprobando bateria de pruebas para arbol binario de busqueda \(muchas instrucciones y pocos indice\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP2.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_15000_100_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_15000_100.dat" "erroresMP2.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# =============================
# === Run SEQUENCE Estudiantes MM ===
# =============================
echo Ejecutando el programa con secuencia \(muchas instrucciones y muchos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" SEQUENCE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes MP ===
echo Comprobando bateria de pruebas para secuencia \(muchas instrucciones y muchos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM1.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_SEQUENCE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM1.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


# ==========================================================================================
# ==============================
# === Run BTREE Estudiantes MM ===
# ==============================
echo Ejecutando el programa con arbol binario de busqueda \(muchas instrucciones y muchos indices\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat"

"$JAVA_HOME_JDK/bin/java" -cp "$TMP_FOLDER$BIN_DIR:juego_de_pruebas_2022/lib/TAD_modified.jar" "$MAIN" BTREE "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo Ejecucion sin errores
read -rsp $'Press any key to continue...\n' -n 1 key

# === Comprobacion Estudiantes MM ===
echo Comprobando bateria de pruebas para arbol binario de busqueda \(muchas instrucciones y muchos indice\)
# === DESCOMENTAR LA SIGUIENTE LINEA SI SE QUIERE IMPRIMIR LA ORDEN === 
# echo "$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM2.txt"

"$JAVA_HOME_JDK/bin/java" -jar "juego_de_pruebas_2022/lib/Comparator.jar" "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat" "juego_de_pruebas_2022/salida/Salida_30000_100000.dat" "erroresMM2.txt"

if [ $? -eq 1 ] 
    then
	echo Ejecucion con errores
	read -rsp $'Press any key to continue...\n' -n 1 key
	exit 1
fi

echo
echo
read -rsp $'Press any key to continue...\n' -n 1 key
# ===


exit









