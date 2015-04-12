def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count do |char_tuple|
      char_tuple.first != char_tuple.last && !char_tuple.include?(nil)
    end
  end