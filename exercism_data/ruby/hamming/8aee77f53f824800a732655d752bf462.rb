require 'forwardable'

class Strand
  extend Forwardable
  attr_reader :nucleotides

  def_delegator :@nucleotides, :length

  def initialize(strand_text)
    @nucleotides = []
    @nucleotides = strand_text.chars
  end

end

class StrandCollection
  attr_reader :original, :copy

  def initialize(original, copy)
    @original = Strand.new(original)
    @copy = Strand.new(copy)
  end

  def distance
    return 0 if original.length == 0 && copy.length == 0

    calculate_distance
  end

  private

  def calculate_distance
    distance = 0

    if copy.length > original.length
      shorter = original
      longer = copy
    else
      shorter = copy
      longer = original
    end

    shorter.nucleotides.each_with_index do |n, i|
      distance += 1 unless n == longer.nucleotides[i]
    end

    distance
  end
end

class Hamming
  def self.compute(original, copy)
    strands = StrandCollection.new(original, copy)

    strands.distance
  end
end
