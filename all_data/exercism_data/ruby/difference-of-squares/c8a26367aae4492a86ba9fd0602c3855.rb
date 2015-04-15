class Squares

  def initialize n 
    @n = n
  end

  def n
    @n
  end

  def square_of_sums
    sum = (1..n).reduce(:+)
    sum * sum
  end

  def sum_of_squares
    (n ** 3)/3.0 + (n ** 2)/2.0 + n/6.0
  end

  def difference
    square_of_sums - sum_of_squares
  end
  
end
