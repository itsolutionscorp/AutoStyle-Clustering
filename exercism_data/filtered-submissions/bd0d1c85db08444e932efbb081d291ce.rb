def compute(a, b)
   (0..a.length - 1).map{ |n| a[n] == b[n] ? 0 : 1 }.reduce(:+)
  end