# Source: @zenspider http://exercism.io/submissions/73caaeaf65b636ce7ca4dfc2
class Squares
  attr_accessor :n

  def initialize n
    self.n = n
  end

  def sum_of_squares
    # http://en.wikipedia.org/wiki/Square_pyramidal_number
    (n * (n + 1) * (2*n + 1)) / 6
  end

  def square_of_sums
    # http://en.wikipedia.org/wiki/Triangular_number
    ((n**2 + n) / 2) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
