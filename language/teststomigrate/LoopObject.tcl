# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc loop {n} {
  set obj(i) 0
  while {$obj(i) < $n} {
    incr obj(i)
  }
  return $obj(i)
}

set i 0
while {$i < 20} {
  loop 1000
  incr i
}
puts [loop 1000]
