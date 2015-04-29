class Hamming
  def self.compute(first, second)
    new(first, second).find_hamming_distance
  end

  attr_reader :strand1, :strand2

  def initialize(strand1, strand2)
    @strand1 = strand1.chars
    @strand2 = strand2.chars
  end

  def find_hamming_distance
    min = [strand1.length, strand2.length].min
    (0...min).count { |i| strand1[i] != strand2[i] }
  end
end
