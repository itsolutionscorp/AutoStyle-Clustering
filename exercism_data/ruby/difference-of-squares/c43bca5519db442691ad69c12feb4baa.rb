class Squares

  def initialize(n)
    @n = n
  end

  def square_of_sums
    (0..@n).reduce(:+)**2
  end

  def sum_of_squares
    (0..@n).map {|n| n**2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
