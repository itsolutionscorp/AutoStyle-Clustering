class Hamming
  def self.compute(dna1, dna2)
    distance = 0
    dna1.chars.zip(dna2.chars).each do |n1, n2|
      distance+=1 if n1 != n2 and n2 != nil
    end
    return distance
  end
end
