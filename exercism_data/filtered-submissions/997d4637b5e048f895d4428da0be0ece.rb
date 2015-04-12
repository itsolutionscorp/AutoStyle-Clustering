def compute(a,b)
    return 0 if a == b
    min_length = [a.size, b.size].min
    (0...min_length).count {|index| a[index] != b[index] }
  end