class Hamming
  def compute(strand1, strand2)
    (strand1.chars.zip(strand2.chars)).count do |nucleotide_a, nucleotide_b|
      nucleotide_a != nucleotide_b
    end
  end
end
