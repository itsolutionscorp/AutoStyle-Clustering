class Squares
  def initialize(x)
    @numbers = x
  end
  
  def square_of_sums
    @square_of_sums = ((1..@numbers).reduce(:+))**2
  end
  
  def difference
    square_of_sums
    sum_of_squares
    @square_of_sums - @sum
  end
  
  def sum_of_squares
    @sum = 0
    for x in (1..@numbers)
      @sum += x**2
    end
    @sum
  end
  
end
