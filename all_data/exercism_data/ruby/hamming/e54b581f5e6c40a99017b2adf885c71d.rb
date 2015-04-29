class Hamming

  def self.compute(strand1, strand2)
    new(strand1, strand2).distance
  end

  attr_reader :strand1, :strand2

  def initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def distance
    (0...shortest_strand).count do |position|
      strand_difference?(position)
    end
  end

  private

  def strand_difference?(position)
    strand1[position] != strand2[position]
  end

  def shortest_strand
    [strand1.size, strand2.size].min
  end
end 
