class Squares

  def initialize(number)
    @sum = number*(number+1)/2
    @multiplier = number*2 +1
  end

  def square_of_sums
    @sum ** 2
  end

  def sum_of_squares
    (@sum * @multiplier)/3
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
