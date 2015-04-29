class Hamming
  def self.compute(dna1, dna2)
    j = -1
    dna1.split("").count{ |i| i!=dna2[j+=1] && dna2[j]!=nil }
  end
end
