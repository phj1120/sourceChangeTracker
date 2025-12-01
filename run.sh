#!/bin/bash
# Unix/Linux/Mac용 실행 스크립트

if [ ! -f "out/ChangedSourcePrinterApplication.class" ]; then
    echo "Class files not found. Please compile first using ./compile.sh"
    exit 1
fi

# 애플리케이션 실행
java -cp out ChangedSourcePrinterApplication
