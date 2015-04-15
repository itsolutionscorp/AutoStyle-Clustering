class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def square_of_sums
    (1..n).reduce(:+)**2
  end

  def sum_of_squares
    (1..n).reduce { |sum, i| sum + i**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
