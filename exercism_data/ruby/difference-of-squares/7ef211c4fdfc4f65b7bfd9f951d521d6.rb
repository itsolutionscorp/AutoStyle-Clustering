class Squares

  attr_reader :range

  def initialize n
    @range = 1..n
  end

  def square_of_sums
    range.inject(:+) ** 2
  end

  def sum_of_squares
    range.map { |i| i ** 2 }.inject(:+)
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
