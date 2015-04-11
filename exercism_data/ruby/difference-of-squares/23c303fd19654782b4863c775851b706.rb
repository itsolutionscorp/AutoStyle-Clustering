class Squares
  def initialize(num)
    @num = num
  end 

  def difference 
    square_of_sums - sum_of_squares
  end 

  def square_of_sums
    sum_upto_num * sum_upto_num
  end 

  def sum_of_squares 
    (@num + 1).times.inject(){|result, n| result += (n * n)}
  end 

  private 

  def sum_upto_num
    @sum ||= ((@num+1).times.reduce(:+))
  end 

end
