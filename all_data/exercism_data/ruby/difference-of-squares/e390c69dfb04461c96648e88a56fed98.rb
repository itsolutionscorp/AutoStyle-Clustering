# used to calculate sum of squares or square of sums
class Squares
  attr_reader :max

  def initialize(max)
    @max = max
  end

  def square_of_sums
    (1..max).inject { |sum, n| sum + n } ** 2
  end

  def sum_of_squares
    (1..max).inject { |sum, n| sum + (n ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
