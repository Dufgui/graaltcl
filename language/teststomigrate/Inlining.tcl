# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc a {} {return 42}

proc b {} {return [a]}

proc c {} {return [b]}

proc d {} {return [c]}

proc e {} {return [c]}

proc f {} {return [c]}

proc g {} {return [expr {[d] + [e] + [f]}]}

set i 0
set result 0
while {$i < 10000} {
  set result [expr {$result + [g]}]
  incr i
}
puts $result
