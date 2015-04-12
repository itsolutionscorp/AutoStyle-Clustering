def compute(strand1, strand2)
    strand1, strand2 = strand2, strand1 if strand1.size > strand2.size

    strand1.chars.each.with_index.count { |char, i| strand2[i] != char }
  end