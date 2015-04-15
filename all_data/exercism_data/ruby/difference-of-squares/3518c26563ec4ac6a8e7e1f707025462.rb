class Squares

  def initialize(arg)
  	@number = arg
  end

  def square_of_sums
    sum = (0..@number).inject { |sum, n| sum + n }
    sum**2
  end

  def sum_of_squares
    (0..@number).inject { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
