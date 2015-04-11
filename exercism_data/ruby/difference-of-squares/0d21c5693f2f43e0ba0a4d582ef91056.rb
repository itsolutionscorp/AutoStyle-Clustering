# Solution to Project Euler Problem 6
class Squares
  def initialize(n)
    @n = n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    sum_series_to(@n)**2
  end

  def sum_of_squares
    squares = (1..@n).map { |x| x**2 }
    squares.reduce { |a, e| a + e }
  end

  private

  def sum_series_to(x)
    (x + 1) * x / 2
  end
end
