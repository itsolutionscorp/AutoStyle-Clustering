class Squares
  def initialize(n)
    @ns = (1..n)
  end
  def square_of_sums
    @ns.reduce(:+)**2
  end
  def sum_of_squares
    @ns.reduce(0) { |sum, n| sum + n**2 }
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
