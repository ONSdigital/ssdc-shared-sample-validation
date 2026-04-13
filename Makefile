# Set the container runtime based on architecture, default to docker for amd64 and podman for arm64
DOCKER ?= $(shell if [ "$$(uname -m)" = "arm64" ]; then echo podman; else echo docker; fi)

build:
	mvn clean install

build-no-test:
	CONTAINER_CLI=$(DOCKER) mvn clean install -Dmaven.test.skip=true -DdockerCompose.skip=true -Djacoco.skip=true

format:
	mvn fmt:format

format-check:
	mvn fmt:check

check:
	mvn fmt:check pmd:check

test:
	CONTAINER_CLI=$(DOCKER) mvn clean verify jacoco:report

megalint:  ## Run the mega-linter.
	$(DOCKER) run --platform linux/amd64 --rm \
		-v /var/run/docker.sock:/var/run/docker.sock:rw \
		-v $(shell pwd):/tmp/lint:rw \
		oxsecurity/megalinter:v8

megalint-fix:  ## Run the mega-linter and attempt to auto fix any issues.
	$(DOCKER) run --platform linux/amd64 --rm \
		-v /var/run/docker.sock:/var/run/docker.sock:rw \
		-v $(shell pwd):/tmp/lint:rw \
		-e APPLY_FIXES=all \
		oxsecurity/megalinter:v8

clean_megalint: ## Clean the temporary files.
	rm -rf megalinter-reports

lint_check: clean_megalint megalint

