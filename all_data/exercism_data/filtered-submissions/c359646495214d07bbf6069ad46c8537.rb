def compute(a, b)
    combinations = a.chars.zip(b.chars)

    combinations.count { |pair| pair[0] != pair[1] }
  end