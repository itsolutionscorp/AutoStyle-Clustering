class Squares

  def initialize(number)
    @range = 1..number
  end

  def sum_of_squares
    @range.reduce{|sum, number| sum + number**2}
  end

  def square_of_sums
    @range.reduce(:+)**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
