class Squares
  attr_reader :numbers

  def initialize(number)
    @numbers = (1..number)
  end

  def square_of_sums
    numbers.inject(:+) ** 2
  end

  def sum_of_squares
    numbers.inject(0) do |accumulator, num|
      accumulator + (num ** 2)
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
