class Squares
  attr_reader :numbers

  def initialize(num)
    @numbers = 0.upto(num)
  end

  def square_of_sums
    numbers.reduce(:+) ** 2
  end

  def sum_of_squares
    numbers.reduce { |sum, number|
      sum + (number ** 2)
    }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
