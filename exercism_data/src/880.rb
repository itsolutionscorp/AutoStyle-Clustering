class Hamming
  def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).
      select { |pair| pair.first != pair.last }.
      count
  end
end
