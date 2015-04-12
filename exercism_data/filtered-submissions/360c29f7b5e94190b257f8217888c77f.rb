def compute(a, b)
    c = a.chars.zip(b.chars).map { |nuc| nuc[0] == nuc[1] ? 0 : 1 }
    c.reduce(:+)
  end