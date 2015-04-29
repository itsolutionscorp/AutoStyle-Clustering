# Find the difference between the sum of the squares and the square of the sums of the first N natural numbers.

class Squares
  def initialize numbers
    @numbers = numbers
  end

  def square_of_sums
    result = 0
    1.upto(@numbers) {|n| result += n}
    result**2
  end

  def sum_of_squares
    result = 0
    1.upto(@numbers) {|n| result += n**2}
    result
  end

  def difference
    square_of_sums - sum_of_squares
  end


end
