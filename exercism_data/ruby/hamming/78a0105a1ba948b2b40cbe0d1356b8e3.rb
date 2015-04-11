# Two bases to be compared
class BasePair
  attr_accessor :first, :second

  def initialize(bases)
    self.first = bases[0]
    self.second = bases[1]
  end

  def distance
    (first == second) ? 0 : 1
  end
end

# Compute Hamming distance on encapulated strand data
class HammingComputer
  attr_accessor :strand1, :strand2

  def initialize(ary)
    self.strand1, self.strand2 = ary.sort { |aa, bb| aa.length <=> bb.length }
  end

  def compute
    all_base_pairs.reduce(0) { |sum, bases| sum += bases.distance }
  end

  def all_base_pairs
    strand1.chars.zip(strand2.chars).map { |chars| BasePair.new(chars) }
  end
  
end

# Exported interface
class Hamming
  def self.compute(first, second)
     HammingComputer.new([first, second]).compute
  end
end
