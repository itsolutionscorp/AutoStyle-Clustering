def compute string_a, string_b
    nucleotides_a = string_a.split(//)
    nucleotides_b = string_b.split(//)
    duplex = nucleotides_a.zip(nucleotides_b)

    diffs = duplex.map { |nucleotide| nucleotide[0] == nucleotide[1] ? 0 : 1 }
    diffs.reduce(:+)
  end