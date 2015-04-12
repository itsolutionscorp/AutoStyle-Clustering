def compute(a,b)
    raise ArgumentError if a.length != b.length
    [a.length,b.length].max.times.count {|index| a[index] != b[index]}
  end