class Hamming
  def initialize(strand1, strand2)
    @strand1 = strand1.split(//)
    @strand2 = strand2.split(//)
  end

  def self.compute(strand1, strand2)
    new(strand1, strand2).compute
  end

  def compute
    length.times.count { |index| strand1[index] != strand2[index] }
  end

  private

  attr_reader :strand1, :strand2

  def length
    LengthCalculator.new(lengths).shorter
  end

  def lengths
    [strand1.length, strand2.length]
  end
end

class LengthCalculator
  def initialize(elements)
    @elements = elements
  end

  def shorter
    @elements.sort.first
  end
end
