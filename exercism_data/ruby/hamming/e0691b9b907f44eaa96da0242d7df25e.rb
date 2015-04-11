class Hamming
  def self.compute dna1, dna2
    dna1.split('').zip(dna2.split('')).count{|x, y| x != y && y != nil}
  end
end
