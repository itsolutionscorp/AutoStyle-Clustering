class Squares

  def initialize(n)
    @n = n
    @first_n = (1..@n)
  end

  def square_of_sums
    sum = @first_n.inject { |sum, number| sum + number }
    sum ** 2
  end

  def sum_of_squares
    @first_n.inject { |sum, number| sum + number ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
