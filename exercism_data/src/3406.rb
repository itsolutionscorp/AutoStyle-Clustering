def compute(strand_A, strand_B)
    length = [strand_A.length, strand_B.length].min

    # convert each strand to an array of characters of equal length
    b_chars = strand_B.chars.first(length)
    a_chars = strand_A.chars.first(length)

    # compare chars in each strand and count the differences
    a_chars.zip(b_chars).count { |(a, b)| a != b }
  end