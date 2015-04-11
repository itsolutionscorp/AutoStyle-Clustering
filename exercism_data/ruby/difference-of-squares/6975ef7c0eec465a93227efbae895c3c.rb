class Squares
  attr_reader :numbers

  def initialize(numbers)
    @numbers = numbers + 1
  end

  def sum_of_squares
     @square_of_sums ||= numbers.times.reduce { |sum, number| sum + (number ** 2) }
  end

  def square_of_sums
    @square_of_nums ||= numbers.times.reduce(&:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
