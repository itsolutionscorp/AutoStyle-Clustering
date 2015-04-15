class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    # Square of sum of 1..n - use triangle number formula
    ((@num * (@num + 1)) / 2)**2
  end

  def sum_of_squares
    # Sum of all squares 1..n**2
    # Use the sum of squares formula
    (@num * (@num + 1) * (2*@num + 1)) / 6
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
