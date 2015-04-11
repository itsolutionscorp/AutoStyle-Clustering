class Squares
  def initialize n
    @n = n
  end
  
  def sum_of_squares
    (1..@n).map{|i| i*i}.reduce(:+)
  end
  
  def square_of_sums
    ( (1+@n) * @n/2 )**2
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
