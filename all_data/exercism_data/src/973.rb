def compute(a, b)
    a.chars.zip(b.chars).reject { |c| c.first == c.last  }.size
  end