# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc mkobj {} {
  set newobj(z) "zzz"
  return [array get newobj]
}

proc readobj {_obj} {
  array set obj $_obj
  return obj(prop)
}

proc writeobj {_obj value} {
  array set obj $_obj
  set obj(prop) value
  return [array get obj]
}

set obj1(x) 42
puts $obj1(x)

set obj2(o,x) 42
puts $obj2(o,x)
set obj2(o,y) "why"
puts $obj2(o,y)

array set obj3 [mkobj]
puts $obj3(z)

array set obj4 {}
array set obj4 [writeobj [array get obj4] 1]
puts [readobj [array get obj4]]
array set obj4 [writeobj [array get obj4] 2]
puts [readobj [array get obj4]]
array set obj4 [writeobj [array get obj4] "three"]
puts [readobj [array get obj4]]

array set obj5 {}
puts $obj5(x)
