class AbstractSquares
  attr_reader :sum_of_squares, :square_of_sums

  def initialize(number)
    @sum_of_squares = []
    @square_of_sums = 0
    calc_squares(number)
  end

  def difference
    @square_of_sums - @sum_of_squares
  end

  private
  def calc_squares(n)
    "This is abstract class, you must implement this method in descendent classes"
  end
end

class Squares < AbstractSquares
  private
  def calc_squares(n)
    i = 1
    while i <= n 
      @square_of_sums+=i
      @sum_of_squares << i**2
      i+=1
    end
    @sum_of_squares = @sum_of_squares.reduce(:+)
    @square_of_sums**=2
  end
end
