class Squares
  def initialize(peak)
    @numbers = 1..peak
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    @numbers.reduce(:+)**2
  end

  def sum_of_squares
    @numbers.reduce(0) { |sum, n| sum + n**2 }
  end
end
