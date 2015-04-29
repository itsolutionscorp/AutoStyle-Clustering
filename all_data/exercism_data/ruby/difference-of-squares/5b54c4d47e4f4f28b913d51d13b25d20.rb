class Squares
  def initialize(number)
    @num = number
  end

  def square_of_sums 
    sums = sum_it(1..@num)
    sums ** 2
  end

  def sum_of_squares
    squares = (1..@num).map { |n| n**2 }
    sum_it(squares)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum_it(range)
    range.inject(:+)
  end
end
