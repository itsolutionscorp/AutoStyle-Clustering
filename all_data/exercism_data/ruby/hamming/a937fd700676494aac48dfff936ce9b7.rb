class Hamming
  def self.compute(dna1, dna2)
    dna1.length == dna2.length ? Hamming.new(dna1, dna2).compute : 0
  end
  
  def initialize(dna1, dna2)
    @dna1, @dna2 = dna1, dna2
  end
  
  def compute
    count = 0
    (0...@dna1.length).each {|index| count += 1 unless @dna1[index] == @dna2[index] }
    count
  end
end
