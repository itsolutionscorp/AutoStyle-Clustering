class Squares
  attr_accessor :square_of_sums, :sum_of_squares, :difference

  def initialize(n)
    @square_of_sums = sum_formula(n) ** 2
    @sum_of_squares = (1..n).map { |i| i**2 }.reduce(:+)
    @difference = @square_of_sums - @sum_of_squares
  end

  # Calculate the sum from 1 to n in constant time
  def sum_formula(n)
    (n * (n+1)) / 2
  end
end
