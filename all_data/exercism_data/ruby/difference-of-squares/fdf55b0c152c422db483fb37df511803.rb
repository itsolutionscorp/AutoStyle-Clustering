class Squares

  def initialize(n)
  	@n = n
  end

  def range_number
    (1..@n)
  end

  def square_of_sums
    range_number.inject(:+)**2
  end

  def sum_of_squares
	range_number.inject{ |sum, item| sum + item ** 2}	
  end
  
  def difference
  	square_of_sums - sum_of_squares
  end
end
