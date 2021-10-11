# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc double {x} {
  return [expr {2 * $x}]
}

set 2 a
puts $2

puts [double 2]

set [double 2] b
puts $4

set [expr {5 + [double 2]}] c
puts $9
