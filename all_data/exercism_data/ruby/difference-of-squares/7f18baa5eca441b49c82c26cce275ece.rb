class Squares
  def initialize(n)
    @n = n
  end

  def square_of_sums
    0.upto(@n).reduce(:+)**2
  end

  def sum_of_squares
    0.upto(@n).reduce { |a, e| a + e**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
