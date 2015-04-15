class Squares
  attr_reader :square_of_sums 
  attr_reader :sum_of_squares
  attr_reader :difference

  def initialize number
    @square_of_sums = (1..number).reduce(:+) ** 2
    @sum_of_squares = (1..number).map{ |n| n**2 }.reduce(:+)
    @difference = @square_of_sums - @sum_of_squares
  end

end
