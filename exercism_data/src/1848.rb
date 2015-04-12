def compute(a,b)
    # zip chars from a with b
    # select from a and b, compare for match, return count
    a.chars.zip(b.chars).select{|a, b| a!=b}.count
  end