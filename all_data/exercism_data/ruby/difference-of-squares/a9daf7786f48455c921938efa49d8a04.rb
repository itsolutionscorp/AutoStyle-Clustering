class Squares

  def initialize(num)
    @series = 1..num
  end

  def square_of_sums
    @series.reduce(&:+)**2
  end

  def sum_of_squares
    @series.reduce{ |sum, num| sum += num**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
