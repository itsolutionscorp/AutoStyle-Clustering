class Squares
  attr_reader :n
  def initialize(n)
    @n = n
  end

  def sum
    n * (n+1) / 2
  end

  def square_of_sums
    sum * sum
  end

  def sum_of_squares
    n * (n+1) * (2*n+1) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
