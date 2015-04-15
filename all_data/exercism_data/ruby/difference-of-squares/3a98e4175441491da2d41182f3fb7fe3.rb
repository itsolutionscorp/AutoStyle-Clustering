class Squares

  # Single Responsibility: Summe der Quadrate und Quadratsumme aller natürlichen
  # Zahlen von 1..n ausrechen

  attr_accessor :n

  def initialize(number)
    @n = number.abs
  end

  def sum_of_squares(i = n)
    return 0 if i == 0
    return i**2 + sum_of_squares(i-1)
  end

  def square_of_sums
    sum = (1..n).reduce(:+)
    sum**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
