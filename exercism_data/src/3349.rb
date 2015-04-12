def compute(strand_one, strand_two)
    strand_one.chars.each_with_index.count { |s1, i|
      s1 != strand_two[i] }
  end