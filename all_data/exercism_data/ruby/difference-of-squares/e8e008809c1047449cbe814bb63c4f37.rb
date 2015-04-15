#
# Squares calculates the difference of the sum of the squares and the square
# of the sums of the first N natural numbers.  It also exposes methods that
# calculate the square of the sum of the first N naturals, and the sum of the
# squares of the first N naturals.
#
class Squares
  def initialize(n)
    @n = n
  end

  # square_of_sums(3) => 36   i.e. (1+2+3)**2 = 6**2 = 36
  def square_of_sums
    (1..@n).reduce(:+) ** 2
  end

  # sum_of_squares(3) => 14   i.e. (1*1)+(2*2)+(3*3) = 1+4+9 = 14
  def sum_of_squares
    (1..@n).map { |k| k**2 }.reduce(:+)
  end

  # alternate implementation
  def sum_of_squares2
    (1..@n).reduce(0) { |sum, k| sum += k*k }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
