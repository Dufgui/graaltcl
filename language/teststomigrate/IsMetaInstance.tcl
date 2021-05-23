# Copyright {c} 2020, Oracle and/or its affiliates. All rights reserved.
# Licensed under the Universal Permissive License v 1.0 as shown at https://oss.oracle.com/licenses/upl.

proc printTypes {type} {
  puts [string is $type -strict 42]
  puts [string is $type -strict 42000000000000000000000000000000000000000]
  puts [string is $type -strict "42"]
  puts [string is $type -strict [expr {42 == 42}]]
  puts [string is $type -strict [expr {42 != 42}]]
  puts [string is $type -strict 42.42]
  puts [string is $type -strict ""]
  puts [string is $type -strict " "]
  puts [string is $type -strict "A"]
  puts [string is $type -strict "a"]
  puts [string is $type -strict "aaa"]
  puts [string is $type -strict "AAA"]
  puts [string is $type -strict ","]
  puts [string is $type -strict "\0"]
  puts ""
}

printTypes alnum
printTypes alpha
printTypes ascii
printTypes boolean
printTypes control
printTypes digit
printTypes double
printTypes false
printTypes graph
printTypes integer
printTypes lower
printTypes print
printTypes punct
printTypes space
printTypes true
printTypes upper
printTypes wordchar
printTypes xdigit
