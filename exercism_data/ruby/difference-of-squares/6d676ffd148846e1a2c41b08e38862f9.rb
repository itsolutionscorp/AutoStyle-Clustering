class Squares

  def initialize(number)
    @num_sq  = number
    @num_sum = number
  end

  def  square_of_sums
    numbers = []
    numbers << @num_sq && @num_sq -= 1 while @num_sq > 0
    numbers.inject(:+)**2
  end

  def sum_of_squares
    squares, numbers = [], []
    numbers << @num_sum && @num_sum -= 1 while @num_sum > 0
    numbers.map { |n| squares << n**2  }
    squares.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
