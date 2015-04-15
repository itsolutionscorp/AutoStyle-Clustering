class Squares
  def initialize(to)
    @numbers = 1.upto(to)
  end

  def square_of_sums
    @numbers.reduce(:+).abs2
  end

  def sum_of_squares
    @numbers.map(&:abs2).reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
