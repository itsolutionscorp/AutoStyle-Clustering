def compute a, b
    (0..a.length-1).map{|i| a[i] == b[i] ? 0 : 1 }.reduce(:+)
  end