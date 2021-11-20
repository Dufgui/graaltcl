# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc foo {} {
  return "bar"
}

proc f {a b} {
  return "a < b: [expr {$a < $b}]"
}

puts [concat "s" ""]
puts [concat "s" "bar"]
puts "s[foo]"
puts "s[foo]"

puts [concat "" "s"]
puts [concat "bar" "s"]
puts "[foo]s"
puts "[foo]s"

puts [f 2 4]
puts [f 2 "4"]
