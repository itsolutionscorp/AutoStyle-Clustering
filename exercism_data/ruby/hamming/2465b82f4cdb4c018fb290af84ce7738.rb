class Hamming
  def initialize(strand1, strand2)
    @strand1, @strand2 = strand1, strand2
  end

  def self.compute(strand1, strand2)
    new(strand1, strand2).compute
  end

  def compute
    length.times.count do |index|
      strand1.each_char.to_a[index] != strand2.each_char.to_a[index]
    end
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