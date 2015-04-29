class Hamming
  def self.compute(strand1, strand2)
    Strand.new(strand1) <=> Strand.new(strand2)
  end

end

class Strand
  include Comparable
  attr_reader :value

  def initialize(strand)
    @value = strand.chars
  end

  def length
    @value.length
  end

  def <=>(another)
    max = [self.length, another.length].min
    a, b = self.value[0, max], another.value[0, max]
    y = a.zip(b)
    y.inject(0) { |sum, comparison_array| sum += 1 if comparison_array.first != comparison_array.last; sum }
  end
end
