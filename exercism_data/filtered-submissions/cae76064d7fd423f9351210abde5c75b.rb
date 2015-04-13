def compute(strand_A, strand_B)
    length = [strand_A.length, strand_B.length].min


    b_chars = strand_B.chars.first(length)
    a_chars = strand_A.chars.first(length)


    a_chars.zip(b_chars).count { |(a, b)| a != b }
  end