class Squares
  attr_reader :square_of_sums, :sum_of_squares, :difference
  
  def initialize(num)
    @square_of_sums, @sum_of_squares, @difference = 0, 0, 0

    # find square of sums
    (1..num).each { |i| @square_of_sums += i }
    @square_of_sums **= 2
    
    # find sum of squares
    (1..num).each { |i| @sum_of_squares += i**2 }

    # find difference
    @difference = @square_of_sums - @sum_of_squares
  end

end
