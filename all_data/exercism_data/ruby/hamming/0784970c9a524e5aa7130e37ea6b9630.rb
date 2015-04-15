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
  def_delegators :sequence, :[], :each_char
  include Enumerable

  alias_method :each, :each_char

  attr_reader :sequence

  def initialize(sequence)
    @sequence = sequence
  end

  def differences_with(other)
    each_with_index.reduce(0) do |errors, (letter, i)|
      return errors if letter.nil? || other[i].nil?
      errors += 1 if letter != other[i]

      errors
    end
  end
end
