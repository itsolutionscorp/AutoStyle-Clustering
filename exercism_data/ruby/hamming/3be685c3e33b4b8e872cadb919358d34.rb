class Hamming

  def self.compute(a, b)
    Hamming.new(a,b).distance
  end

  def initialize(a, b)
    @strand1 = a
    @strand2 = b
  end

  def distance
    count_differences_in_strands!
  end

private

  def count_differences_in_strands!
    differences = 0
    min_cycles.times do |i|
      differences += strands_differ_at?(i).to_i
    end
    differences
  end

  def strands_differ_at?(index)
    @strand1[index] != @strand2[index]
  end

  def min_cycles
    [@strand1.length, @strand2.length].min
  end
end

class FalseClass; def to_i; 0 end end
class TrueClass; def to_i; 1 end end
