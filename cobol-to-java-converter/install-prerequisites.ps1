# PowerShell Script to Install Java and Maven Prerequisites
# Run this script as Administrator

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "COBOL to Java Converter - Prerequisites Installer" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Function to check if running as Administrator
function Test-Administrator {
    $currentUser = New-Object Security.Principal.WindowsPrincipal([Security.Principal.WindowsIdentity]::GetCurrent())
    return $currentUser.IsInRole([Security.Principal.WindowsBuiltInRole]::Administrator)
}

# Check for Administrator privileges
if (-not (Test-Administrator)) {
    Write-Host "ERROR: This script must be run as Administrator!" -ForegroundColor Red
    Write-Host "Please right-click PowerShell and select 'Run as Administrator'" -ForegroundColor Yellow
    Read-Host "Press Enter to exit"
    exit 1
}

# Check if Chocolatey is installed
Write-Host "Checking for Chocolatey package manager..." -ForegroundColor Yellow
if (!(Get-Command choco -ErrorAction SilentlyContinue)) {
    Write-Host "Chocolatey not found. Installing Chocolatey..." -ForegroundColor Yellow
    
    try {
        Set-ExecutionPolicy Bypass -Scope Process -Force
        [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
        Invoke-Expression ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
        
        Write-Host "✓ Chocolatey installed successfully!" -ForegroundColor Green
        
        # Refresh environment variables
        $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    }
    catch {
        Write-Host "✗ Failed to install Chocolatey: $_" -ForegroundColor Red
        Read-Host "Press Enter to exit"
        exit 1
    }
}
else {
    Write-Host "✓ Chocolatey is already installed" -ForegroundColor Green
}

Write-Host ""

# Check if Java is installed
Write-Host "Checking for Java..." -ForegroundColor Yellow
$javaInstalled = $false
try {
    $javaVersion = java -version 2>&1
    if ($javaVersion -match "version") {
        Write-Host "✓ Java is already installed" -ForegroundColor Green
        Write-Host "  $($javaVersion[0])" -ForegroundColor Gray
        $javaInstalled = $true
    }
}
catch {
    Write-Host "Java not found. Installing OpenJDK 17..." -ForegroundColor Yellow
}

if (-not $javaInstalled) {
    try {
        choco install openjdk17 -y
        Write-Host "✓ Java installed successfully!" -ForegroundColor Green
        
        # Refresh environment variables
        $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    }
    catch {
        Write-Host "✗ Failed to install Java: $_" -ForegroundColor Red
        Read-Host "Press Enter to exit"
        exit 1
    }
}

Write-Host ""

# Check if Maven is installed
Write-Host "Checking for Maven..." -ForegroundColor Yellow
$mavenInstalled = $false
try {
    $mavenVersion = mvn -version 2>&1
    if ($mavenVersion -match "Apache Maven") {
        Write-Host "✓ Maven is already installed" -ForegroundColor Green
        Write-Host "  $($mavenVersion[0])" -ForegroundColor Gray
        $mavenInstalled = $true
    }
}
catch {
    Write-Host "Maven not found. Installing Maven..." -ForegroundColor Yellow
}

if (-not $mavenInstalled) {
    try {
        choco install maven -y
        Write-Host "✓ Maven installed successfully!" -ForegroundColor Green
        
        # Refresh environment variables
        $env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")
    }
    catch {
        Write-Host "✗ Failed to install Maven: $_" -ForegroundColor Red
        Read-Host "Press Enter to exit"
        exit 1
    }
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Verifying Installations" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Verify Java
Write-Host "Java Version:" -ForegroundColor Yellow
try {
    java -version
    Write-Host ""
}
catch {
    Write-Host "✗ Java verification failed" -ForegroundColor Red
}

# Verify Maven
Write-Host "Maven Version:" -ForegroundColor Yellow
try {
    mvn -version
    Write-Host ""
}
catch {
    Write-Host "✗ Maven verification failed" -ForegroundColor Red
}

# Check environment variables
Write-Host "Environment Variables:" -ForegroundColor Yellow
Write-Host "  JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Gray
Write-Host "  MAVEN_HOME: $env:MAVEN_HOME" -ForegroundColor Gray
Write-Host ""

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "Installation Complete!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Next Steps:" -ForegroundColor Yellow
Write-Host "1. Close and reopen your terminal/PowerShell" -ForegroundColor White
Write-Host "2. Navigate to the project directory:" -ForegroundColor White
Write-Host "   cd 'c:\Users\mugdh\OneDrive\Documents\IBM Hackathon\cobol-to-java-converter'" -ForegroundColor Gray
Write-Host "3. Build the project:" -ForegroundColor White
Write-Host "   mvn clean install" -ForegroundColor Gray
Write-Host "4. Run the converter:" -ForegroundColor White
Write-Host "   java -jar target/cobol-to-java-converter-1.0.0-SNAPSHOT.jar src/test/resources/samples/HELLO-WORLD.cbl ./output" -ForegroundColor Gray
Write-Host ""

Read-Host "Press Enter to exit"

# Made with Bob
