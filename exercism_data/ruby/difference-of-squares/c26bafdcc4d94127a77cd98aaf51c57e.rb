class Squares
  def initialize(num)
    @numbers = 1..num
  end

  def sum_of_squares
    @numbers.inject { |sum, n| sum += n**2 }
  end

  def square_of_sums
    @numbers.inject { |sum, n| sum += n } **2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
