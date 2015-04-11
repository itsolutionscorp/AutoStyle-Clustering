class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    (@num*(1+@num)/2)**2
  end
  
  def sum_of_squares
    (@num**3)/3.0 + (@num**2)/2.0 + @num/6.0
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
