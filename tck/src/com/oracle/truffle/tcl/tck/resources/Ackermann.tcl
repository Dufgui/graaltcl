proc ack(m,n) {
  if {$m == 0} {
    return [expr {$n + 1}];
  }
  if {$n == 0} {
      set n 1
  } {
      set n [expr {ack($m, [expr {$n - 1}])}];
  }
  return ack([expr {$m - 1}], n);
}

set result [ack(2,3)]
puts "ack(2,3) = $result"