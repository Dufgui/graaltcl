# The tcl component for GraalVM

Truffle languages can be packaged as components which can be installed into
GraalVM using the [Graal
updater](http://www.graalvm.org/docs/reference-manual/graal-updater/). 
Running `mvn package` in the tcl folder also builds a
`tcl-component.jar`. 
This file is the tcl component for GraalVM and can be installed by
running:

```
/path/to/graalvm/bin/gu install /path/to/tcl-component.jar
```

