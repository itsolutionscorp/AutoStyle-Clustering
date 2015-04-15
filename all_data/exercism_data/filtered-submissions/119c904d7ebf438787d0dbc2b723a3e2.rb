def compute(strand_1, strand_2)
    strand_1.chars
            .zip(strand_2.chars)
            .count{ |v| v[0] != v[1] }
  end