def compute(strand_a, strand_b)
    a = strand_a.chars
    b = strand_b.chars
    h = a.zip(b)
         .take_while { |a,b| a && b != nil}
         .count      { |a,b| a != b }
  end