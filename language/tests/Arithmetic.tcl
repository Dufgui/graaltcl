# Copyright (c) 2021, Guillaume Dufour. All rights reserved.
# Licensed under the Apache License v 2.0 as shown at http://www.apache.org/licenses/.

puts [expr {3 + 4 - 2}]
puts [expr {3 - 4 + 2}]
puts [expr {3 - 4 - 2}]
puts [expr {3 * 4 + 2}]
puts [expr {3 + 4 * 2}]
puts [expr {3 + (4 - 2)}]
puts [expr {3 - (4 + 2)}]
puts [expr {3 - (4 - 2)}]
puts [expr {3 * (4 + 2)}]
puts [expr {3 + (4 * 2)}]
