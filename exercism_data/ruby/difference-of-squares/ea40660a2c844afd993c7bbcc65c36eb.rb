class Squares
  def initialize(num)
    @square_of_sums = (1..num).reduce(:+)**2
    @sum_of_squares = (1..num).inject { |sum, n| sum + n**2 }
    @difference =  @square_of_sums - @sum_of_squares
  end

  attr_reader :square_of_sums, :sum_of_squares, :difference
end
