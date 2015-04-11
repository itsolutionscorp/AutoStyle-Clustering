class Hamming
  def self.compute(a, b)
    strand_a = DNAStrand.new(a)
    strand_b = DNAStrand.new(b)
    strand_a.hamming_distance_to(strand_b)
    # num_compare = [a.length, b.length].min
    # (0...num_compare).count {|i| a[i] != b[i]}
  end
end

class DNAStrand
  attr_accessor :string

  def initialize(string)
    @string = string
  end

  def hamming_distance_to(other)
    shortest_string_length = [length, other.length].min
    (0...shortest_string_length).count {|i| self[i] != other[i]}
  end

  def length
    string.length
  end

  def [](i)
    string[i]
  end
end
