class Squares
  def initialize n
    @n = n
  end
  
  def square_of_sums
    @square_of_sums ||= (1..@n).reduce(:+)**2
  end
  
  def sum_of_squares
    @sum_of_squares ||= (1..@n).map{|x| x**2 }.reduce(:+)
  end
  
  def difference
    @difference ||= square_of_sums - sum_of_squares
  end
end
