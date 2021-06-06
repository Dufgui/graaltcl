# Copyright (c) 2021, Guillaume Dufour. All rights reserved.
# Licensed under the Apache License v 2.0 as shown at http://www.apache.org/licenses/.

proc ret {a} { return $a }

proc dub {a} { return [expr {$a * 2}] }

proc inc {a} { return [expr {$a + 1}] }

proc dec {a} { return [expr {$a - 1}] }

proc call {f v} { return [$f $v] }

puts [ret 42]
puts [dub 21]
puts [inc 41]
puts [dec 43]
puts [call ret 42]
puts [call dub 21]
puts [call inc 41]
puts [call dec 43]
