def compute(a, b)
    a.each_char
      .zip(b.each_char)
      .count { |a, b| b && a != b }
  end