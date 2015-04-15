class Squares
  attr_reader :range

  def initialize(square)
    @range = (1..square)
  end

  def square_of_sums
    range.inject(&:+) ** 2
  end

  def sum_of_squares
    range.inject { |sum, i| sum + i*i }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
