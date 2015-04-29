class Squares
  def initialize(numberOfSquares)
    @numberOfSquares = numberOfSquares
  end

  def square_of_sums
    sum = (1..@numberOfSquares).inject{|sum,x|sum+x}
    sum*sum
  end

  def sum_of_squares
    (1..@numberOfSquares).inject{|sum,x|sum+x*x}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
