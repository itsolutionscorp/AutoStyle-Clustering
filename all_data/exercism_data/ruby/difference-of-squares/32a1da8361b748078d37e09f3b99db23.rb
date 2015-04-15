class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    ((1..@num).reduce(:+))**2
  end

  def sum_of_squares
    squared_ary = (1..@num).map{|x| x**2}
    squared_ary.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
