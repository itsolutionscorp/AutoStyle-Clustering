class Squares

  attr_reader :max
  def initialize(max)
    @max = max
  end

  def sum_of_squares
    @max * (2*@max**2 + 3*@max + 1) / 6
  end

  def square_of_sums
    range = (0..@max)
    range.inject(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end

puts Squares.new(1009999999).square_of_sums
