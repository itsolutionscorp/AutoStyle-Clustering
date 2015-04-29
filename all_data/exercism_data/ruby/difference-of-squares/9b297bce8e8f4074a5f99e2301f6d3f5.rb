class Squares
  attr_accessor :square_of_sums, :sum_of_squares, :difference

  def initialize(n)
    @square_of_sums = (1..n).reduce(:+) ** 2
    @sum_of_squares = (1..n).map { |i| i**2 }.reduce(:+)
    @difference = @square_of_sums - @sum_of_squares
  end
end
