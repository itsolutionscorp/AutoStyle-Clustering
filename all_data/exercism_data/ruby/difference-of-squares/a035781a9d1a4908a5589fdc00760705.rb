class Squares
  attr_reader :square_of_sums
  attr_reader :sum_of_squares
  attr_reader :difference

  def initialize(n)
    array = (1..n).to_a
    @sum_of_squares = array.map { |x| x**2 }.inject(:+)
    @square_of_sums = array.inject(:+)**2
    @difference = square_of_sums - sum_of_squares
  end
end
