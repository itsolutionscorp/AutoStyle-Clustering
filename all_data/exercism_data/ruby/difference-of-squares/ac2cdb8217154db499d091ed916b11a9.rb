class Squares
  def initialize(positive_integer)
    @numbers = 1..positive_integer
  end

  def sum_of_squares
    @numbers.map {|number| number.abs2}.reduce(:+)
  end

  def square_of_sums
    @numbers.reduce(:+).abs2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
