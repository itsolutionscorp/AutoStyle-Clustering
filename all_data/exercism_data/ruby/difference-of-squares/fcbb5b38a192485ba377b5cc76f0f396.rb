class Squares
  attr_accessor :square_of_sums, :sum_of_squares, :difference

  def initialize(n)
    @square_of_sums = sum_formula(n) ** 2
    @sum_of_squares = sum_of_squares_formula(n)
    @difference = @square_of_sums - @sum_of_squares
  end

  def sum_formula(n)
    (n * (n+1)) / 2
  end

  def sum_of_squares_formula(n)
    (n * (n+1) * (2*n+1)) / 6
  end
end
