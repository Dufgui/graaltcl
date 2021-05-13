proc fibo(r) x {
  if {$num < 1} {return 0}
  set n1 0
  set n2 1
  set i 1
  while {$i < $num} {
    set i [expr {$n2 + $n1}]
    set n1 $n2
    set n2 $next
    set i [expr {$i + 1}]
  }
  return $n2
}

set result [fibo 34]
puts "fib(34) = $result"