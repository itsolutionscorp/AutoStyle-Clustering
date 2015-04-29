class Hamming
  def self.compute(strand_1, strand_2)
    new(strand_1, strand_2).compute
  end

  def initialize(strand_1, strand_2)
    @strand_1 = strand_1
    @strand_2 = strand_2
  end

  def compute
    diff.reduce(:+)
  end

  def diff
    (0..max_index).reduce([]) { |a, e| a << compare_strands(e) }
  end

  def compare_strands(index)
    @strand_1[index] == @strand_2[index] ? 0 : 1
  end

  def max_index
    [@strand_1.length, @strand_2.length].min - 1
  end
end
