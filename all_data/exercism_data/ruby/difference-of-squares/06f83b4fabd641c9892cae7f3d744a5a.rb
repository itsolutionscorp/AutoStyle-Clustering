class Squares
  @num_upto = nil
  def initialize(num)
    @num_upto = num
  end
  
  def sum_of_squares
    (0..@num_upto).reduce{|sum,n| sum += n*n}
  end
 
  def square_of_sums
    (0..@num_upto).reduce(:+) **2
  end

  def difference
    (self.sum_of_squares - self.square_of_sums).abs
  end
end
