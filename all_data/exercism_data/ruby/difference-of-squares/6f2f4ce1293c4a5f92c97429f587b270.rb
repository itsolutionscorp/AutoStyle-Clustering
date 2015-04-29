class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def difference
    square_of_sums - sum_of_squares
  end

  # Triangle number
  def square_of_sums
    sums = n * (n + 1) / 2
    sums * sums
  end

  # Square pyramidal number
  def sum_of_squares
    n * (n + 1) * (2 * n + 1) / 6
  end
end
