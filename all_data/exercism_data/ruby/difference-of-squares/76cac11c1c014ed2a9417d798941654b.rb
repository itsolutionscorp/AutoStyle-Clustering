# Calculate the square of the sum of natural numbers from 1 to a given value
# or the sum of the squares of the same range
class Squares
  attr_reader :count

  def initialize(count)
    @count = count
  end

  def square_of_sums
    square(sum_up_to count)
  end

  def sum_of_squares
    sum(squares_up_to count)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sum(sequence)
    sequence.inject(:+)
  end

  def sum_up_to(num)
    sum((1..num))
  end

  def square(num)
    num * num
  end

  def squares_up_to(num)
    (1..num).map { |x| square x }
  end
end
