# squares.rb
class Squares
  attr_reader :n

  def initialize(n)
    @n = n
  end

  def sum_of_squares
    (2*n**3 + 3*n**2 + n)/6
  end

  def square_of_sums
    (n**4 + 2*n**3 + n**2)/4
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
