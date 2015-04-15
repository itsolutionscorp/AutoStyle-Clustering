class Squares

  def initialize(nombre)
    @tableau = (1..nombre)
  end

  def square_of_sums
    sum = @tableau.inject {|sum, i| sum + i}
    sum ** 2
  end

  def sum_of_squares
    @tableau.inject {|square, i| square + (i**2)}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
