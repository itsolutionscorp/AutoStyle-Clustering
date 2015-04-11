# Solution to Project Euler Problem 6
class Squares
  def initialize(n)
    @n = n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    (1..@n).reduce(:+)**2
  end

  def sum_of_squares
    (1..@n).reduce { |a, e| a + e**2 }
  end
end
