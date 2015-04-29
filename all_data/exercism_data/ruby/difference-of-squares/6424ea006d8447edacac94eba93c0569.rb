class Squares
  attr_reader :numbers

  def initialize(n = 1)
    @numbers = Array(1..n)
  end

  def sum_of_squares
    numbers.map { |n| n**2 }.reduce(&:+)
  end

  def square_of_sums
    numbers.reduce(&:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
