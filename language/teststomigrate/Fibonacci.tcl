# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc fib {num} {
  if {$num < 1} {return 0}
  set n1 0
  set n2 1
  set i 1
  while {$i < $num} {
    set next [expr {$n2 +$n1}]
    set n1 $n2
    set n2 $next
    incr i
  }
  return $n2
}

set i 1
while {$i <= 10} {
  puts "$i: [fib $i]"
  incr i
}
