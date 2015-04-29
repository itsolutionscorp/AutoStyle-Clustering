class Hamming
  def initialize(strand1, strand2)
    @strand1, @strand2 = strand1, strand2
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
    [strand1.length, strand2.length].min
  end
end
