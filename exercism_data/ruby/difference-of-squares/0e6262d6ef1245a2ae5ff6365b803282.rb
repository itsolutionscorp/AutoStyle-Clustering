class Squares
  attr_accessor :num
  
  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = (1..@num).reduce(:+)
    sum ** 2
  end

  def sum_of_squares
    (1..@num).map{ |e|
      e**2
    }.reduce(:+)
  end

  def difference 
    square_of_sums - sum_of_squares
  end
end
