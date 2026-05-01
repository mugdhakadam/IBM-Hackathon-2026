# Installation Guide for Prerequisites

This guide will help you install Java 11+ and Maven 3.6+ on Windows.

## Installing Java Development Kit (JDK)

### Option 1: Using Chocolatey (Recommended)

If you have Chocolatey package manager installed:

```powershell
# Install OpenJDK 17 (LTS version)
choco install openjdk17 -y

# Verify installation
java -version
```

### Option 2: Manual Installation

1. **Download JDK**:
   - Visit: https://adoptium.net/
   - Download: Eclipse Temurin JDK 17 (LTS) for Windows x64
   - File: `OpenJDK17U-jdk_x64_windows_hotspot_17.x.x.msi`

2. **Install JDK**:
   - Run the downloaded `.msi` file
   - Follow the installation wizard
   - Default location: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot\`
   - ✅ Check "Add to PATH" option

3. **Verify Installation**:
   ```powershell
   java -version
   ```
   
   Expected output:
   ```
   openjdk version "17.0.x" 2024-xx-xx
   OpenJDK Runtime Environment Temurin-17.0.x+x (build 17.0.x+x)
   OpenJDK 64-Bit Server VM Temurin-17.0.x+x (build 17.0.x+x, mixed mode, sharing)
   ```

4. **Set JAVA_HOME** (if not set automatically):
   ```powershell
   # Open PowerShell as Administrator
   [System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot', 'Machine')
   
   # Verify
   echo $env:JAVA_HOME
   ```

## Installing Apache Maven

### Option 1: Using Chocolatey (Recommended)

```powershell
# Install Maven
choco install maven -y

# Verify installation
mvn -version
```

### Option 2: Manual Installation

1. **Download Maven**:
   - Visit: https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.x-bin.zip` (Binary zip archive)

2. **Extract Maven**:
   - Extract to: `C:\Program Files\Apache\maven`
   - Final path: `C:\Program Files\Apache\maven\apache-maven-3.9.x\`

3. **Set Environment Variables**:
   
   Open PowerShell as Administrator:
   
   ```powershell
   # Set MAVEN_HOME
   [System.Environment]::SetEnvironmentVariable('MAVEN_HOME', 'C:\Program Files\Apache\maven\apache-maven-3.9.x', 'Machine')
   
   # Add Maven to PATH
   $currentPath = [System.Environment]::GetEnvironmentVariable('Path', 'Machine')
   $newPath = $currentPath + ';C:\Program Files\Apache\maven\apache-maven-3.9.x\bin'
   [System.Environment]::SetEnvironmentVariable('Path', $newPath, 'Machine')
   ```

4. **Restart PowerShell** and verify:
   ```powershell
   mvn -version
   ```
   
   Expected output:
   ```
   Apache Maven 3.9.x
   Maven home: C:\Program Files\Apache\maven\apache-maven-3.9.x
   Java version: 17.0.x, vendor: Eclipse Adoptium
   ```

## Quick Installation Script

Save this as `install-prerequisites.ps1` and run as Administrator:

```powershell
# Check if Chocolatey is installed
if (!(Get-Command choco -ErrorAction SilentlyContinue)) {
    Write-Host "Installing Chocolatey..."
    Set-ExecutionPolicy Bypass -Scope Process -Force
    [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072
    iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
}

# Install Java
Write-Host "Installing OpenJDK 17..."
choco install openjdk17 -y

# Install Maven
Write-Host "Installing Maven..."
choco install maven -y

# Verify installations
Write-Host "`nVerifying installations..."
Write-Host "`nJava version:"
java -version

Write-Host "`nMaven version:"
mvn -version

Write-Host "`nInstallation complete! Please restart your terminal."
```

## Verification Steps

After installation, verify everything is working:

```powershell
# Check Java
java -version
javac -version

# Check Maven
mvn -version

# Check environment variables
echo $env:JAVA_HOME
echo $env:MAVEN_HOME
```

## Troubleshooting

### Issue: "java is not recognized"

**Solution**:
1. Verify Java is installed: Check `C:\Program Files\Eclipse Adoptium\`
2. Add to PATH manually:
   - Open System Properties → Environment Variables
   - Edit PATH variable
   - Add: `C:\Program Files\Eclipse Adoptium\jdk-17.x.x-hotspot\bin`
3. Restart terminal

### Issue: "mvn is not recognized"

**Solution**:
1. Verify Maven is installed
2. Add to PATH manually:
   - Add: `C:\Program Files\Apache\maven\apache-maven-3.9.x\bin`
3. Restart terminal

### Issue: "JAVA_HOME not set"

**Solution**:
```powershell
# Set JAVA_HOME
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-17.0.x-hotspot', 'Machine')
```

## Alternative: Using Scoop Package Manager

```powershell
# Install Scoop
iwr -useb get.scoop.sh | iex

# Install Java and Maven
scoop bucket add java
scoop install openjdk17
scoop install maven
```

## Next Steps

Once prerequisites are installed:

1. Navigate to project directory:
   ```powershell
   cd "c:\Users\mugdh\OneDrive\Documents\IBM Hackathon\cobol-to-java-converter"
   ```

2. Build the project:
   ```powershell
   mvn clean install
   ```

3. Run the converter:
   ```powershell
   java -jar target/cobol-to-java-converter-1.0.0-SNAPSHOT.jar src/test/resources/samples/HELLO-WORLD.cbl ./output
   ```

## Additional Resources

- **Java Downloads**: https://adoptium.net/
- **Maven Downloads**: https://maven.apache.org/download.cgi
- **Chocolatey**: https://chocolatey.org/
- **Scoop**: https://scoop.sh/

---

Need help? Check the troubleshooting section or consult the official documentation.