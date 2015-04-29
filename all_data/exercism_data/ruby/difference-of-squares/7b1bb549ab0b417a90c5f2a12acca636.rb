class Squares
  attr_accessor :value

  def initialize(number)
    @value = number
  end

  def square_of_sums
    (1..value).reduce(:+)**2
  end
    
  def sum_of_squares
    (1..value).map{|x| x**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
