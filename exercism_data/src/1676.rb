class Hamming
  def compute(dna1,dna2)
    hamming_dist = 0
    dna1.chars.zip(dna2.chars).each{|a,b| hamming_dist += 1 if a != b}
    return hamming_dist
  end
end
