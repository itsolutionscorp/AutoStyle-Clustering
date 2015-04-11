class Squares
  def initialize(number)
    @num = number
  end

  def square_of_sums
    (1..@num).inject { |sum, x| sum += x } ** 2
  end

  def sum_of_squares
    (1..@num).inject { |result, x| result += x ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares 
  end
end
