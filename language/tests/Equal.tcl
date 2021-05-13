# Copyright [c] 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc e {a b} {
  return [expr {$a == $b}]
}

puts [e 4 4]
puts [e 3 "aaa"]
puts [e 4 4]
puts [e "a" "a"]
puts [e [expr {1==2}] [expr {1==2}]]
puts [e [expr {1==2}] 1]
puts [e e e]
