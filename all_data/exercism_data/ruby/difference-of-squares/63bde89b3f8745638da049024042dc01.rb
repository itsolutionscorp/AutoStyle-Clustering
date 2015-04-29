class Squares
  attr_reader :square_of_sums 
  attr_reader :sum_of_squares
  attr_reader :difference

  def initialize number
    @square_of_sums = (number * (number + 1) / 2)**2
    @sum_of_squares = number * (number + 1) * (2 * number + 1) / 6
    @difference = @square_of_sums - @sum_of_squares
  end

end
