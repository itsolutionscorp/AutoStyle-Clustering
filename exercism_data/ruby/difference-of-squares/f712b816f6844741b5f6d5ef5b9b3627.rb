class Squares
  attr_reader :array
  def initialize(number)
    @array = [*1..number]
  end

  def square_of_sums
    array.reduce(:+)**2
  end

  def sum_of_squares
    array.map {|i| i**2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
