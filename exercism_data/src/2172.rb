class Hamming
  def compute(dna1, dna2)
    hamming = 0
    (dna1.size).times { |x| hamming += 1 unless dna1[x] == dna2[x] }
    hamming
  end
end
