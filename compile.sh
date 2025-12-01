#!/bin/bash
# Unix/Linux/Mac용 컴파일 스크립트

echo "Compiling Changed Source Tracking Application..."

# 출력 디렉토리 생성
mkdir -p out

# 모든 Java 파일 컴파일
javac -d out -encoding UTF-8 \
    src/main/java/ChangedSourcePrinterApplication.java \
    src/main/java/domain/*.java \
    src/main/java/enums/*.java \
    src/main/java/service/*.java \
    src/main/java/display/*.java \
    src/main/java/config/*.java \
    src/main/java/exception/*.java

if [ $? -eq 0 ]; then
    echo ""
    echo "Compilation successful!"
    echo ""
    echo "Run with: java -cp out ChangedSourcePrinterApplication [branch1] [branch2] ..."
else
    echo ""
    echo "Compilation failed!"
    exit 1
fi
