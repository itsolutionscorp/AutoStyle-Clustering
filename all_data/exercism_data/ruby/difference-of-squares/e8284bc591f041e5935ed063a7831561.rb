class Squares
  attr_accessor :num, :square_of_sums, :sum_of_squares, :difference
  def initialize(num)
    @num = num
    @square_of_sums = square_sums
    @sum_of_squares = sum_squares
    @difference = diff
  end

  def square_sums
    (1..num).reduce(:+) ** 2
  end

  def sum_squares
    (1..num).inject { |sum, n| sum + (n ** 2) }
  end

  def diff
    (square_of_sums - sum_of_squares).abs
  end
end
