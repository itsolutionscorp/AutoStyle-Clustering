class Squares
  def initialize(value)
    @value = value
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    (1..@value).map { |x| x**2 }.inject(0, &:+)
  end

  def square_of_sums
    (1..@value).inject(0, &:+) ** 2
  end
end
