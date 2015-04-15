# Linear time for trying out enumerable.
# For constant time:
#   Summation of n   = n(n+1)/2
#   Summation of n^2 = (1/6)n(n+1)(2n+1)
class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    first_n_nums.reduce(:+)**2
  end

  def sum_of_squares
    first_n_nums.inject { |sum, num| sum + num**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def first_n_nums
    1..number
  end
end
