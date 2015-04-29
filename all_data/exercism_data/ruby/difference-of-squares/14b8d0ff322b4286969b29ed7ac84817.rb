class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    (@n * (@n + 1) / 2)**2
  end

  def sum_of_squares
    (@n * (@n + 1) * (2 * @n + 1)) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
