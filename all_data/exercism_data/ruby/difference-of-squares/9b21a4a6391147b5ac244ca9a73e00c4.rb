class Squares
  def initialize(n)
    @diferences = (1..n)
  end

  def sum_of_squares
    @diferences.reduce(0) { |a, e| (e**2) + a }
  end

  def square_of_sums
    @diferences.reduce(0, :+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
