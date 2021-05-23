# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc recursion {n} {
  set local 42

  if {$n > 0} {
    recursion [expr {$n - 1}]
  } else {
    set local "abc"
  }

  puts $local
}

recursion 3
