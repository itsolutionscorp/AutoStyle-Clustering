class Squares
  attr_accessor :num

  def initialize num
    @num = (1..num)
  end

  def sum_of_squares
    num.inject(0){ |sum, i| sum + i**2 }
  end

  def square_of_sums
    num.reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
