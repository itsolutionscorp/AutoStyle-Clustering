class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def numbers
    (1..number)
  end

  def square_of_sums
    numbers.inject(:+) ** 2
  end

  def sum_of_squares
    numbers.map { |n| n ** 2 }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
