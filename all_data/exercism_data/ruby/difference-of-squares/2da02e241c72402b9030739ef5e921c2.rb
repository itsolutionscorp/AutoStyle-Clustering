class Squares
  
  def initialize(num)
    @num = num
  end

  def square_of_sums
    [*1..@num].inject { |sum, n| sum + n } ** 2
  end

  def sum_of_squares
    squares = [*1..@num].map { |n| n ** 2 }
    squares.inject { |sum, n| sum + n }
  end

  def difference
    square_of_sums - self.sum_of_squares
  end
end
