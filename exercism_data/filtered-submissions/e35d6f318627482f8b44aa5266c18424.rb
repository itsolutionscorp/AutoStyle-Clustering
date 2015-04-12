def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count { |x, y| x != y unless x.nil? || y.nil? }
  end