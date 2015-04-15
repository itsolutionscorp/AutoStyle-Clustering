class Squares
  def initialize(n)
    @n = n
  end

  def sum_of_squares
    (2 * n ** 3 + 3 * n ** 2 + n) / 6
  end

  def square_of_sums
    ((n ** 2 + n) / 2) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  attr_reader :n
end
