# Squares
# Computes difference between the sum of the squares and the
# square of the sums of the first N natural numbers
class Squares
  def initialize(num)
    @n = num
  end

  # Returns the square of the sum of the first +n+ natural numbers
  def square_of_sums
    return (@n * (@n + 1) / 2)**2
  end

  # Returns the sum of the squares of the first +n+ natural numbers
  def sum_of_squares
    return @n * (@n + 1) * (2 * @n + 1) / 6
  end

  # Returns the difference between square_of_sums and sum_of_squares
  def difference
    return square_of_sums - sum_of_squares
  end
end
