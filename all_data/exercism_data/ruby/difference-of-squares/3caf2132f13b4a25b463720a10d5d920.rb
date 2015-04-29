class Squares

  def initialize(value)
    @value = value
  end

  def square_of_sums
    (1..@value).inject { |sum, x| sum + x} ** 2
  end

  def sum_of_squares
    (1..@value).inject { |square, x| square + x**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
