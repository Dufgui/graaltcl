# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc doIt {a} {
  puts "Initial stack trace:"
  puts [stacktrace]

  set hello 123
  puts "After 123 assignment:"
  puts [stacktrace]

  helloEqualsWorld
  puts "After hello assignment:"
  puts [stacktrace]
}

set i 0
while {$i < 10} {
  doIt $i
  incr i
}
