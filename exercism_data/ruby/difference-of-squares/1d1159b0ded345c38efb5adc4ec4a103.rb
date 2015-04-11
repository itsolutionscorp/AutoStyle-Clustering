class Squares
  def initialize(value)
    @value = value
  end

  def square_of_sums()
    (@value * (@value + 1) / 2) ** 2
  end

  def sum_of_squares()
    @value * (@value + 1) * (2 * @value + 1) / 6
  end

  def difference()
    square_of_sums() - sum_of_squares()
  end
end
