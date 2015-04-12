#
require 'pry'
class Hamming
  def self.convert_lengths(left, right)
    left = left.take(right.size) if left.size > right.size
    right = right.take(left.size) if right.size > left.size
    [left, right]
  end

  def self.compare_equal_lengths(left, right)
    left.zip(right).map { |x, y| x == y }.count(false)
  end

  def self.compute(original_left, original_right)
    left, right = [original_left, original_right].map(&:chars)
    left, right = convert_lengths(left, right)
    compare_equal_lengths(left, right)
  end
end