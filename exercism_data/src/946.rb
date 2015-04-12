class Hamming
  def compute(strand_1, strand_2)
    return strand_1.chars.zip(strand_2.chars)
      .count do |nucleotide_1, nucleotide_2|
        nucleotide_1 != nucleotide_2
      end
  end
end
