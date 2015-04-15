class Squares
  def initialize max
    @max = max
  end

  def sum_of_squares
    (2 * max ** 3 + 3 * max ** 2 + max) / 6
  end

  def square_of_sums
    (max * (max + 1) / 2) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  attr_reader :max
end
