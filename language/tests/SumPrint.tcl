# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc loop {n} {
  set i 0
  set sum 0
  while {$i <= $n} {
    set sum [expr {$sum + $i}]
    incr i
  }
  return $sum
}

set i 0
while {$i < 20} {
  loop 10000
  incr i
}
puts [loop 10000]
