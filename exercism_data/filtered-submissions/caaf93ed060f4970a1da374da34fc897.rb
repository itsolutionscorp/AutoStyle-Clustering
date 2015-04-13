def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).inject(0) { |diff, (c1, c2)|
      return diff if c1.nil? or c2.nil?
      diff + (c1 == c2 ? 0 : 1)
    }
  end