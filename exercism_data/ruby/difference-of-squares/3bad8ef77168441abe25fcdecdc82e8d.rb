class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = (1..@num).reduce(:+)
    sum**2
  end

  def sum_of_squares
    squares = (1..@num).map { |n| n**2 }
    squares.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
