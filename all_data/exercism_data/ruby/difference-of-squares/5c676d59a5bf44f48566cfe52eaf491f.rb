class Squares
  POWER = 2

  def initialize(args)
    @numbers_range = 1..args
  end

  def square_of_sums
    @numbers_range.inject(:+) ** POWER
  end

  def sum_of_squares
    @numbers_range.map { |n| n ** POWER }.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
