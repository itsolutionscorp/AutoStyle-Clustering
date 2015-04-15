class Hamming
  def self.compute(dna1, dna2)
    dna1 = dna1.slice(0...dna2.size) if dna1.size > dna2.size
    dna2 = dna2.slice(0...dna1.size) if dna2.size > dna1.size
    return 0 if dna1 == dna2 && dna1.size == 1
    return 1 if dna1.size == 1
    dna1[0] == dna2[0] ? (return compute(dna1[1..-1], dna2[1..-1])) : (return 1 + compute(dna1[1..-1], dna2[1..-1]))
  end
end
