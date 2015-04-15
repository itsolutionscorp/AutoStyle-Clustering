class Squares
  def initialize(max)
    @max = max
  end

  def square_of_sums
    sums = 1.upto(@max).inject(:+)
    sums**2
  end

  def sum_of_squares
    1.upto(@max).inject{|sum, n| sum + n**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
