class Squares

  attr_reader :number, :range

  def initialize(number)
    @number = number
    @range = 1..number
  end

  def sum_of_squares
    squares = range.reduce{|sum, number| sum + number**2}
  end

  def square_of_sums
    range.reduce(:+)**2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

end
