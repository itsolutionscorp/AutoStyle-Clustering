class Squares
  attr_reader :value

  def initialize value
    @value = value
  end

  def sum_of_squares
    (value * (value + 1) * (2 * value + 1)) / 6
  end

  def square_of_sums
    ((value + 1) * value / 2 ) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
