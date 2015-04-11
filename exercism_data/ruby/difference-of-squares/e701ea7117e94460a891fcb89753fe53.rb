class Squares
  def initialize (n)
    @n = n
  end
  def sum_of_squares
    (1..@n).reduce{|a, i| a + i**2}
  end
  def square_of_sums
    -> x { x**2 }[(1..@n).reduce(:+)]
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
