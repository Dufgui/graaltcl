# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc left {x} {
  puts "left"
  return $x
}

proc right {x} {
  puts "right"
  return $x
}

set t [expr {10 == 10}];# true
set f [expr {10 != 10}];# false
puts [expr {[left $f] && [right $f]}]
puts [expr {[left $f] && [right $t]}]
puts [expr {[left $t] && [right $f]}]
puts [expr {[left $t] && [right $t]}]
puts ""
puts [expr {[left $f] || [right $f]}]
puts [expr {[left $f] || [right $t]}]
puts [expr {[left $t] || [right $f]}]
puts [expr {[left $t] || [right $t]}]
