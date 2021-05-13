# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc loop {n _obj name} {
  array set obj $_obj
  set obj($name) 0
  while {$obj($name) < $n} {
    incr obj($name)
  }
  return $obj($name)
}

set i 0
while {$i < 20} {
  array set new {}
  loop 1000 [array get new] "prop"
  incr i
}
array set new {}
puts [loop 1000 [array get new] "prop"]
