class Hamming
  attr_reader :dna_1, :dna_2

  def self.compute(dna_1, dna_2)
    new(dna_1, dna_2).compute
  end

  def initialize(dna_1, dna_2)
    length = [dna_1.length, dna_2.length].min

    @dna_1 = dna_1[0..length-1]
    @dna_2 = dna_2[0..length-1]
  end

  def compute
    mutations = dna_1.chars.zip(dna_2.chars).select {|pair| pair[0] != pair[1]}
    mutations.count
  end
end
