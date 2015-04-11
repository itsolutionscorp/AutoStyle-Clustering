# Difference between the sum of the squares and the square of the sum
class Squares
  attr_accessor :num_squares

  def initialize(num_squares)
    @num_squares = num_squares
  end

  def square_of_sums
    (1..num_squares).reduce(:+)**2
  end

  def sum_of_squares
    (1..num_squares).map { |num| num**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
