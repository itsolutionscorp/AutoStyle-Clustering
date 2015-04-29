class Hamming
  def self.compute(left, right)
    new(left, right).distance
  end

  def initialize(left, right)
    @left = left
    @right = right
  end

  def distance
    sum_of_differences
  end

  private

  def differences
    (0..min_size-1).map{|index| @left[index] == @right[index] ? 0 : 1}
  end

  def sum_of_differences
    differences.inject(:+)
  end

  def min_size
    [@left.size, @right.size].min
  end

end
