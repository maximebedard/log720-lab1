@echo off

set LOG720_LAB1_PROJECT_ROOT=%cd%
set path=%LOG720_LAB1_PROJECT_ROOT%

set JAVA_HOME=C:\oracle\java\jdk7_71_x64
set path=%JAVA_HOME%\bin;%PATH%

set MVN_HOME=c:\apache\maven\3.2.2
set path=%MVN_HOME%\bin;%PATH%

set JACORB_HOME=%LOG720_LAB1_PROJECT_ROOT%\vendor\jacorb-3.6.1
set path=%JACORB_HOME%;%PATH%

set JACORB_CONFIGS_PATH=%LOG720_LAB1_PROJECT_ROOT%\config\local
