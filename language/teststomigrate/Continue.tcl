# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

set i 0
set j 0
while {$i < 1000} {
  incr i
  if {$i >= 942} {
    continue
  }
  incr j
}

puts $i
puts $j
