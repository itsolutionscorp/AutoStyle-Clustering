class Squares
  attr_reader :n

  def initialize(n)
     @n = n
  end

  def square_of_sums
    x = (1..n).reduce(:+)
    x * x
  end

  def sum_of_squares
    (1..n).map {|x| x * x}.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
