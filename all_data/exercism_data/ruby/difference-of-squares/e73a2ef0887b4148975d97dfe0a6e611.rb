# Utility class to calculate sum of squares, squares of sum
# and differences there of.
class Squares
  def initialize(upper_limit)
    @upper_limit = upper_limit
    @sum         = (upper_limit * (upper_limit + 1)) / 2
  end

  def sum_of_squares
    Array(1..@upper_limit).reduce { |sum, i|  sum + i**2 }
  end

  def square_of_sums
    @sum**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
