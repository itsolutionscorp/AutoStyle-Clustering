class Hamming
  # Public: Compute the Hamming distance between two strands.
  #
  # strand_a - String of strand characters
  # strand_b - String of strand characters
  #
  # Example:
  #
  # > Hamming.compute('A', 'A')
  # => 0
  # > Hamming.compute('GGACG', 'GGTCG')
  # => 1
  #
  # Returns an Integer.
  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).compute
  end

  def initialize(strand_a, strand_b)
    @strand_a = strand_a
    @strand_b = strand_b
  end

  attr_reader :strand_a, :strand_b

  # Internal: Returns 0 immediately if the strands are equal, otherwise it
  # returns the result of distance.
  #
  # Returns an Integer.
  def compute
    return 0 if strand_a == strand_b
    distance
  end

  # Internal: Computes hamming distance for the two strands. Uses the shorter
  # strand as the comparison length.
  #
  # Returns an Integer.
  def distance
    distance = 0
    length   = [strand_a.length, strand_b.length].min

    length.times do |n|
      if strand_a[n] != strand_b[n]
        distance += 1
      end
    end

    distance
  end
end
