class Squares
  attr_reader :numbers

  def initialize(number)
    @numbers = (1..number)
  end

  def square_of_sums
    numbers.inject(&:+).abs2
  end

  def sum_of_squares
    numbers.map(&:abs2).inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
