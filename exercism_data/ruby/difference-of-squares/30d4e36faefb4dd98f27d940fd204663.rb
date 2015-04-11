class Squares
  def initialize(natural)
    @naturals = (1..natural)
  end

  def square_of_sums
    @naturals.reduce(:+)**2
  end

  def sum_of_squares
    @naturals.reduce { |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
