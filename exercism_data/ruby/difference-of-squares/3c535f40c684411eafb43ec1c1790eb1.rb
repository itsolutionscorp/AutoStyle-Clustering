class Squares
  attr_accessor :sums
  def initialize(number)
    @sums = (1..number).to_a
  end

  def square_of_sums
    @sums.inject(&:+) ** 2
  end

  def sum_of_squares
    @sums.map { |x| x ** 2 }.inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
