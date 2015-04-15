class Squares

  attr_accessor :number

  def initialize(number)
    @number = number
  end

  def sum_of_squares
    square = -> (n) { n**2 }
    (0..number).map(&square).reduce(:+)
  end

  def square_of_sums
    (0..number).reduce(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
