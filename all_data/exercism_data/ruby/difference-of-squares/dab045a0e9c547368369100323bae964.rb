class Squares
  
  def square_of_sums
    x = 0
	@@range.each {|i| x += i}
	x**2
  end
  
  def sum_of_squares
    x = 0
	@@range.each {|i| x += i**2}
	x
  end
  
  def initialize(n)
    @@range = (1..n)
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
  
end
