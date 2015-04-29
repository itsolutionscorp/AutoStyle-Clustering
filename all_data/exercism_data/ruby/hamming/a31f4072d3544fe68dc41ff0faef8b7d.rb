class Hamming
  def self.compute(s1, s2)
    self.new(s1, s2).distance
  end

  def initialize(s1, s2)
    @s1, @s2 = s1, s2
  end

  def distance
    strands_match? ? 0 : compute_distance
  end

  private

  def strands_match?
    @s1 == @s2
  end

  def compute_distance
    comparative_range.inject(0) { |distance, i| distance + mutation_value(i) }
  end

  def comparative_range
    (0...shorter_strand_length)
  end

  def mutation_value(index)
    @s1[index] == @s2[index] ? 0 : 1
  end

  def shorter_strand_length
    [@s1.length, @s2.length].min
  end
end
