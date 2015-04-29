class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sums = (1..number).inject(&:+)
    square_of_sums = sums**2
  end

  def sum_of_squares
    (1..number).inject { |sum_of_squares, n| sum_of_squares + (n ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
