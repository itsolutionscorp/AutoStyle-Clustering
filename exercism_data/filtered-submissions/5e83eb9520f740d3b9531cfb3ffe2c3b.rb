def compute(a,b)
    pairs = a.chars.zip(b.chars)
    pairs.count { |p| p.uniq.compact.length > 1 } 
  end