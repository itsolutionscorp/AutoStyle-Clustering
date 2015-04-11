class Squares
  def initialize(count)
    @numbers = 0..count
  end

  def square_of_sums
    @numbers.reduce(:+) ** 2
  end

  def sum_of_squares
    @numbers.reduce { |x, y| x + (y * y) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
