build:
	mvn clean install

build-no-test:
	mvn clean install -Dmaven.test.skip=true -DdockerCompose.skip=true -Djacoco.skip=true

format:
	mvn fmt:format

format-check:
	mvn fmt:check

check:
	mvn fmt:check pmd:check

test:
	mvn clean verify jacoco:report

