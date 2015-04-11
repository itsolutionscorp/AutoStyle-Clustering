class Hamming
  def self.compute(string1, string2)
    pair = StrandPair.new(string1, string2)
    pair.distance
  end
end

class StrandPair
  def initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def distance
    shared_positions.select {|pos| !same_at(pos)}.count
  end

  def same_at(position)
    @strand1[position] == @strand2[position]
  end

  def shared_positions
    (0..min_length-1)
  end

  def min_length
    [@strand1.length, @strand2.length].min
  end
end
