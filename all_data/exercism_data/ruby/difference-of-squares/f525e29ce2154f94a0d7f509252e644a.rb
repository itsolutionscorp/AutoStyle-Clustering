class Squares

  def initialize(n)
    @range = (0..n)
  end

  def square_of_sums
    @range.inject(&:+)**2
  end

  def sum_of_squares
    @range.map { |i| i**2 }.inject(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
