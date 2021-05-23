# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

set i 0
set sum 0
while {$i <= 10000} {
  set sum [expr {$sum + $i}]
  incr i
}
puts $sum
