# Squares of number sequences
class Squares
  # Instantiate a new object that operates on the first n natural numbers.
  #
  # @param n [Integer]
  def initialize(n)
    @sequence = 1.upto(n)
  end

  # Calculate the difference between the sum of squares and the square of sums.
  #
  # @return [Numeric]
  def difference
    square_of_sums - sum_of_squares
  end

  # Calculate the square of sums.
  #
  # @return [Numeric]
  def square_of_sums
    square(sum(sequence))
  end

  # Calculate the sum of squares.
  #
  # @return [Numeric]
  def sum_of_squares
    sum(square(sequence))
  end

  private

  attr_reader :sequence

  # @overload square(number)
  #   Calculate the square of a number.
  #
  #   @param number [Numeric]
  #   @return [Numeric]
  # @overload square(numbers)
  #   Calculate the square of each number in a collection.
  #
  #   @param numbers [Enumerable<Numeric>]
  #   @return [Enumerable]
  def square(number)
    case number
    when Numeric then number**2
    when Enumerable then number.map(&method(:square))
    end
  end

  # Calculate the sum of a collection of numbers.
  #
  # @param numbers [Enumerable<Numeric>]
  # @return [Numeric]
  def sum(numbers)
    numbers.reduce(&:+)
  end
end
