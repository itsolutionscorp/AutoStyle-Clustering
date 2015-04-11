class Squares
  def initialize(num_numbers)
    @numbers = 1..num_numbers
  end

  def square_of_sums
    @numbers.reduce( :+ )**2
  end

  def sum_of_squares
    squares = @numbers.map { |e| e**2 }
    squares.reduce( :+ )
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
