# Copyright (c) 2021, Guillaume Dufour. All rights reserved.
# Licensed under the Apache License v 2.0 as shown at http://www.apache.org/licenses/.

set i 0
<<<<<<< HEAD:language/teststomigrate/Break.tcl
while {i < 1000} {
if {i >= 942} {
  break
}
set i [expr {$i + 1}]
}
puts i
=======
while {$i < 1000} {
  if {$i >= 942} {
    break
  }
  incr i
}

puts $i
>>>>>>> f296370e61096fa6cd5ed35955134332d0686d9d:language/tests/Break.tcl
