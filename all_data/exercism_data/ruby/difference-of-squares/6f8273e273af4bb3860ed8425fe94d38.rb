class Squares

  def initialize(square)
    @square = square
  end

  def square_of_sums
    square_sums = 1.upto(@square).inject{|sum, number| sum + number }
    square_sums**2
  end

  def sum_of_squares
    sum_square = 1.upto(@square).inject{|sum, number| sum + (number**2)}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
