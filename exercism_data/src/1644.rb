def compute(a, b)
    a.split('').zip(b.split('')).select{ |a1, b1| a1 && b1 && a1 != b1 }.size
  end