class Squares

  def initialize(max)
    @max = max
  end

  def square_of_sums
    @square_of_sums ||= (1..@max).reduce(:+)**2
  end

  def sum_of_squares
    @sum_of_sqaures ||= (1..@max).reduce { |sum, number| sum + number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
