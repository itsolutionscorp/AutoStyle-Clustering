def compute(strand1, strand2)
    chars1 = strand1.chars.take(strand2.length)
    chars2 = strand2.chars

    pairs = chars1.zip(chars2)
                  .count { |char1, char2| char1 != char2 }
  end