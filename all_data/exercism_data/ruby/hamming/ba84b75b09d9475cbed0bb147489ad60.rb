class Hamming
  def self.compute dna1, dna2
    dna1, dna2 = dna1.to_a, dna2.to_a
    dna1.zip(dna2).count{|x, y| x != y && y != nil}
  end
end

class String
  def to_a
    self.split('')
  end
end
