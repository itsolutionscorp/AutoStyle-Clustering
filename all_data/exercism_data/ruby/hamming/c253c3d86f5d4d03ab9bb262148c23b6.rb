class Hamming
  def self.compute(strand1, strand2)
    new(strand1, strand2).distance
  end

  attr_reader :strand1, :strand2

  def initialize(strand1, strand2)
    @strand1 = strand1.chars
    @strand2 = strand2.chars
  end

  def distance
    min = [strand1.length, strand2.length].min
    (0...min).count { |i| strand1[i] != strand2[i] }
  end
end
