def compute(strand1, strand2)
    strand1.chars.select.with_index {|char, index| char != strand2[index]}.count
  end