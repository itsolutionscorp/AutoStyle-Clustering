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

  def square_of_sums
    sum = (1..@n).reduce(:+)                    # 1+2+...+n
    sum * sum
  end

  def sum_of_squares
    (1..@n).reduce(0) { |sum, i| sum += i*i }   # 1*1 + 2*2 + ... n*n
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
