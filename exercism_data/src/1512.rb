def compute(a, b)
    unless a.length == b.length
      raise ArgumentError, "Expected strands of the same length, got #{a} and #{b}"