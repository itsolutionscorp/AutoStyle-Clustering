class Squares

  def initialize(num)
    @num = (1..num)
  end

  def square_of_sums
    @num.inject(:+)**2
  end

  def sum_of_squares

    @num.inject(0) { |result, element| result += element**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
