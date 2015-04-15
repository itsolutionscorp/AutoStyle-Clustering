class Squares
  attr_reader :n
  def initialize(n)
    @n = n
  end

  # https://en.wikipedia.org/wiki/Summation
  def square_of_sums
    ((n + 1) * n / 2) ** 2
  end

  # https://en.wikipedia.org/wiki/Square_pyramidal_number
  def sum_of_squares
    n * ( n + 1 ) * (2*n + 1) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
