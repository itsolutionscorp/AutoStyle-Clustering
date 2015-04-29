class Hamming

  def self.compute(strand1, strand2)
    hamming = hamming || Hamming.new(strand1, strand2)
    hamming.evaluate_strands
  end

  def initialize(strand1, strand2)
    @strand1 = strand1
    @strand2 = strand2
  end

  def evaluate_strands
    (0..size - 1).reduce(0) do | count, index |
      count += return_one_if_not_equal_at_index(index)
    end
  end

  def size
    [@strand1.size, @strand2.size].min
  end

  def return_one_if_not_equal_at_index(index)
    (@strand1[index] != @strand2[index]) ? 1 : 0
  end


end
