class Squares
  def initialize(value)
    @value = value
  end

  def sum_of_squares
    (1..@value).reduce{ |sum, number| sum + number**2 }
  end

  def square_of_sums
    (1..@value).reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
