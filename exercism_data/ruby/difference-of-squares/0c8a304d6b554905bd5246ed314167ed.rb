class Squares
  
  def square_of_sums
	(@range.inject(0){|total,i| total + i})**2
  end
  
  def sum_of_squares
	@range.inject(0){|total,i| total + i**2}
  end
  
  def initialize(n)
    @range = (1..n)
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
  
end
