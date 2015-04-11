class Squares
  attr_reader :numbers

  def initialize(high_number)
    @numbers = (1..high_number)
  end

  def square_of_sums
    sums = numbers.inject(:+)
    sums ** 2
  end

  def sum_of_squares
    numbers.inject { |sum, n| sum + n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
