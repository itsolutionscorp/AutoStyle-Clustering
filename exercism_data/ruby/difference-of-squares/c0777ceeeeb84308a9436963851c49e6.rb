class Squares
  attr_reader :numbers

  def initialize(numbers)
    @numbers = numbers + 1
  end

  def sum_of_squares
    numbers.times.reduce { |sum, number| sum + (number ** 2) }
  end

  def square_of_sums
    numbers.times.reduce(&:+) ** 2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
