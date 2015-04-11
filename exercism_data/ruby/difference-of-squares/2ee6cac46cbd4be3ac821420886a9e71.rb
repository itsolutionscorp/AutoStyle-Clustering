class Squares
  attr_reader :square_of_sums, :sum_of_squares, :difference

  def initialize(n)
    @sum_of_squares = (1..n).inject { |sum, x| sum + x**2 }
    @square_of_sums = (1..n).inject(:+)**2
    @difference = square_of_sums - sum_of_squares
  end
end
