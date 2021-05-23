# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc mkobj {} {
  set newobj(z) "zzz"
  return [array get newobj]
}

proc readobj {_obj name} {
  array set obj $_obj
  return $obj($name)
}

proc writeobj {_obj name value} {
  array set obj $_obj
  set obj($name) $value
  return [array get obj]
}

array set obj4 {}
array set obj4 [writeobj [array get obj4] "prop" 1]
puts [readobj [array get obj4] "prop"]
array set obj4 [writeobj [array get obj4] "prop" 2]
puts [readobj [array get obj4] "prop"]
array set obj4 [writeobj [array get obj4] "prop" "three"]
puts [readobj [array get obj4] "prop"]

set obj5(prop0) 1
set i 1
while {$i < 10} {
  array set obj5 [writeobj [array get obj5] "prop$i" [expr {[readobj [array get obj5] "prop[expr {$i - 1}]"] * 2}]]
  incr i
}
parray obj5
