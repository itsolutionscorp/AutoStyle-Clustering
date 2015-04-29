# Find the difference between the sum of the squares and the square of the sums of the first N natural numbers.

class Squares
  def initialize numbers
    @numbers = numbers
  end

  def square_of_sums
    result = 0
    for n in 1..@numbers
      result += n
    end
    result**2
  end

  def sum_of_squares
    result = 0
    for n in 1..@numbers
      result += n**2
    end
    result
  end

  def difference
    square_of_sums - sum_of_squares
  end


end
