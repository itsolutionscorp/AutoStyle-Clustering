class Squares

  attr_reader :n

  def initialize(n)
    @n = n
  end

  def square_of_sums
    @square_of_sums ||= ((2 + n - 1) * n * 0.5) ** 2
  end

  def sum_of_squares
    @sum_of_squares ||= n * (n + 1) * (2 * n + 1) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
