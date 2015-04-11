class DnaStrand
  def self.parse(strand)
    new(strand.split(''))
  end

  def initialize(bases)
    @bases = bases
  end

  def count_point_mutations(other)
    min_elements_count = [self.length, other.length].min

    result = 0
    min_elements_count.times do |i|
      result += 1 if self[i] != other[i]
    end
    result
  end

  protected

  def [](index)
    @bases[index]
  end

  def length
    @bases.length
  end
end

module Hamming
  def self.compute(first, other)
    first_strand = DnaStrand.parse(first)
    other_strand = DnaStrand.parse(other)

    first_strand.count_point_mutations(other_strand)
  end
end
