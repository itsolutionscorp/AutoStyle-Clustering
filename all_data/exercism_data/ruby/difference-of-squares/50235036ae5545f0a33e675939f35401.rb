module FigurateNumber
  def triangular_nth(n)
    n * (n + 1) / 2
  end

  def square_pyramid_nth(n)
    n * (n + 1) * (n * 2 + 1) / 6
  end
end

class Squares
  include FigurateNumber
  
  def initialize(n)
    @n = n
  end

  def square_of_sums
    triangular_nth(@n) ** 2
  end

  def sum_of_squares
    square_pyramid_nth(@n)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
