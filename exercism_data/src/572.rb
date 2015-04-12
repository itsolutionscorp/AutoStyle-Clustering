def compute(a, b)
    a[0...b.size].split('').reject.with_index { |c, i| c == b[i] }.size
  end