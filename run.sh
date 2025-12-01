#!/bin/bash
# Unix/Linux/Mac용 실행 스크립트

if [ ! -f "out/ChangedSourcePrinterApplication.class" ]; then
    echo "Class files not found. Please compile first using ./compile.sh"
    exit 1
fi

# config.properties를 out 디렉토리로 복사
cp -f config.properties out/ 2>/dev/null

# 애플리케이션 실행
java -cp out ChangedSourcePrinterApplication "$@"
