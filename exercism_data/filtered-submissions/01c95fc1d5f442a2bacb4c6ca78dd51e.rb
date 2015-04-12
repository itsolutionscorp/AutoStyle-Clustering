class Hamming
  def compute(dna1, dna2)
    dna1.size.times.count { |i| dna1[i] != dna2[i] }
  end
end
