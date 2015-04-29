class Squares
  def initialize(input)
    @input = input
  end

  def square_of_sums
    ((1..@input).inject(:+))**2
  end

  def sum_of_squares
    ((1..@input).map { |index| index**2 }).inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
