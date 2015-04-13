def compute(a,b)
    return 0 if a == b
    a, b = b, a if a.length > b.length
    a.chars.zip(b.chars).count {|aa, bb| aa != bb}
  end