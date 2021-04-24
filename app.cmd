@echo off

set app=%2%

goto %1%

:create
    @echo creating app '%app%'
    @mkdir "src/test/java/%app%"
    @mkdir "src/test/java/%app%/pages"
    @mkdir "src/test/java/%app%/repositories"
    @mkdir "src/test/java/%app%/steps"
    @mkdir "src/test/java/%app%/tests"
    @mkdir "src/test/java/%app%/tests/regression"
    @mkdir "src/test/java/%app%/tests/unit"
    @mkdir "src/test/java/%app%/tests/sanity"
    @mkdir "src/test/java/%app%/tests/smoke"

    @mkdir "src/test/resources/%app%"
    @mkdir "src/test/resources/%app%/config"
    @mkdir "src/test/resources/%app%/data"
    @mkdir "src/test/resources/%app%/suites"
    goto end


:end
    @echo done
