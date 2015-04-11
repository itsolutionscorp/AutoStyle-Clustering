class Squares
  def initialize(n)
    @n = n
  end

  def sum_of_squares
    # http://en.wikipedia.org/wiki/Square_pyramidal_number
    (2 * n ** 3 + 3 * n ** 2 + n) / 6
  end

  def square_of_sums
    # http://en.wikipedia.org/wiki/Triangular_number
    ((n ** 2 + n) / 2) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  attr_reader :n
end
