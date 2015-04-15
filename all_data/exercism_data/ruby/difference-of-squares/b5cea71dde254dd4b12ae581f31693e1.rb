class Squares
  attr_accessor :n

  def initialize n
    self.n = n
  end

  def sum_of_squares
    (1..n).map { |m| m ** 2 }.inject(:+)
  end

  def square_of_sums
    ((n**2 + n) / 2) ** 2 # diagonal number = sum from 1 to n = n * n + n / 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
