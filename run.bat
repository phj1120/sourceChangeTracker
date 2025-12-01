@echo off
REM Windows용 실행 스크립트

if not exist "out\ChangedSourcePrinterApplication.class" (
    echo Class files not found. Please compile first using compile.bat
    pause
    exit /b 1
)

REM config.properties를 out 디렉토리로 복사
copy /Y config.properties out\ >nul 2>&1

REM 애플리케이션 실행
java -cp out ChangedSourcePrinterApplication %*

pause
