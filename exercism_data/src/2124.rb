class Hamming
  def compute(dna1, dna2)
    dna1, dna2 = [dna1, dna2].sort_by(&:length)
    dna1.chars.zip(dna2.chars).select { |(a, b)| a != b }.count
  end
end
