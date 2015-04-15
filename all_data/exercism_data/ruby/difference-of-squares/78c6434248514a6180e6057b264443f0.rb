class Squares
  attr_reader :x
  def initialize(x)
    @x = x
  end

  def square_of_sums
    (1..x).reduce(:+)**2
  end

  def sum_of_squares
    (1..x).map {|x| x**2}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
