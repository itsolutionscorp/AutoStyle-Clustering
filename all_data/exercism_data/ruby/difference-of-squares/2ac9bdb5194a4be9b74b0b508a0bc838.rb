class Squares
  def initialize(n)
    @num = n
  end

  def square_of_sums
    @result_square_of_sum = (1..@num).reduce(:+).**2
  end

  def sum_of_squares
    @result_sum_of_squares = (1..@num).inject(0){|sum, n| sum + (n**2)}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end