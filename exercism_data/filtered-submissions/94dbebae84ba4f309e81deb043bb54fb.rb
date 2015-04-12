def compute(a, b)
    a, b = [a, b].sort_by(&:length)
    a.chars.zip(b.chars).count { |a, b| a != b }
  end