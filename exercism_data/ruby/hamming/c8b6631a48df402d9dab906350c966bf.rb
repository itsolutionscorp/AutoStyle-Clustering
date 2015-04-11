class Hamming
  def self.compute(strand_a, strand_b)
    Strand.new(strand_a).hamming_distance(Strand.new(strand_b).chain)
  end
end

class Strand
  attr_reader :chain

  def initialize(chain)
    @chain = chain
  end

  def hamming_distance(other_chain)
    short, long = [chain, other_chain].sort_by(&:length)
    short.chars.each_with_index.count do |nucleic_acid , index|
      nucleic_acid != long[index]
    end
  end
end
