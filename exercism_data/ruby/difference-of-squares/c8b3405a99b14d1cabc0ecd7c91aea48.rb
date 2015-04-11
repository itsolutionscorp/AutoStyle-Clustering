class Squares
  attr_accessor :square_of_sums, :sum_of_squares, :difference

  def initialize(x)
    @square_of_sums = ((0..x).reduce(:+))**2
    @sum_of_squares = ((0..x).collect { |i| i**2 }).reduce(:+)
    @difference = self.square_of_sums - self.sum_of_squares
  end
end
