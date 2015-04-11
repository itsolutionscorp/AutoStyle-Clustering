class Squares

  attr_reader :num
  
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (num*(1+num)/2)**2
  end
  
  def sum_of_squares
    (num**3)/3.0 + (num**2)/2.0 + num/6.0
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
