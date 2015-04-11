require 'forwardable'

class Hamming
  class << self
    # Returns the number of differences between two strands as an Integer
    def compute(first_sequence, second_sequence)
      first_strand  = DNAStrand.new(first_sequence)
      second_strand = DNAStrand.new(second_sequence)

      first_strand.differences_with(second_strand)
    end
  end
end

class DNAStrand
  extend Forwardable
  def_delegators :sequence, :[], :chars
  include Enumerable

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def count
    chars.count
  end

  def differences_with(other)
    shortest_strand = [count, other.count].min

    shortest_strand.times.count do |i|
      self[i] != other[i]
    end
  end
end
