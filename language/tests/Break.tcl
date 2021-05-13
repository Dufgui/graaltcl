# Copyright (c) 2021, Guillaume Dufour. All rights reserved.
# Licensed under the Apache License v 2.0 as shown at http://www.apache.org/licenses/.

set i 0
while {$i < 1000} {
  if {$i >= 942} {
    break
  }
  incr i
}

puts $i
