class Squares
  
  def initialize(n)
    @n = n
  end
  
  def square_of_sums
    (1..@n).reduce(:+)**2
  end
  
  def sum_of_squares
    (1..@n).reduce(0) {|sum,i| sum+i**2}   
  end  

  def difference
    (sum_of_squares - square_of_sums).abs
  end
  
end
  
