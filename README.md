# ssdc-shared-sample-validation
Shared/common classes which are used to validate sample data for Survey Collection &amp; Ingestion


## Building
Podman and Docker are both supported for building and running the application.
By default the Makefile will use `docker` unless you are on an `arm64` architecture (e.g. M1/M2 Mac) in which case it will use `podman`.
You can override this by setting the `DOCKER` environment variable to either `docker` or `podman`.
For example, to force using `docker` on an M1/M2 Mac:
```shell
DOCKER=docker make <command>
```

To run all the tests and build the image

```shell
   make build
```

Just build the image

```shell
    make build-no-test
```