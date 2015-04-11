class Squares

  attr_reader :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    1.upto(num).reduce(:+)**2
  end

  def sum_of_squares
    1.upto(num).reduce(0) { |sum, x| sum + x**2 }
  end

  def difference
     square_of_sums - sum_of_squares 
  end
end
