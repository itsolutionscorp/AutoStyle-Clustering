def compute(a,b)
    diff = 0
    a.chars.zip(b.chars) { |a,b| diff += 1 if a != b && !a.nil? && !b.nil? }
    diff
  end