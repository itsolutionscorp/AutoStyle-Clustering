class Squares
  def initialize(max)
    @range = 1.upto(max)
  end

  def square_of_sums
    sums = @range.inject(:+)
    sums**2
  end

  def sum_of_squares
    @range.inject{ |sum, n| sum + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
