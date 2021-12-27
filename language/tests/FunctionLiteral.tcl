# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc add {a b} {
  return [expr {$a + $b}]
}

proc sub {a b} {
  return [expr {$a - $b}]
}

proc foo {f} {
  puts [$f 40 2]
}

foo add
foo sub
