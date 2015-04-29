class Squares
  def initialize(num)
    @num = num
  end
  
  def square_of_sums
    (1..@num).inject(:+) ** 2 
  end
  
  def sum_of_squares
    (1..@num).inject(0) {|sum, x| sum = sum + x ** 2}
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
    
end
