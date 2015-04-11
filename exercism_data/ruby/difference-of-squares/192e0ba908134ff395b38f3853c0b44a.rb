class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    ((@n + 1) * @n / 2) ** 2
  end

  def sum_of_squares
    (2 * @n + 1) * (@n + 1) * @n / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
