def compute(first, second)
    first, second = second, first if first.length > second.length
    first.chars.zip(second.chars).count { |a, b| a != b }
  end