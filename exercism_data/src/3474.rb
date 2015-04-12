class Hamming
  def compute string_a, string_b
    nucleotides_a = string_a.chars
    nucleotides_b = string_b.chars
    duplex = nucleotides_a.zip(nucleotides_b)

    diffs = duplex.filter { |nucleotide| nucleotide[0] != nucleotide[1]}
    diffs.length
  end
end