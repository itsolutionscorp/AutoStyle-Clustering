class Squares
  def initialize(number)
    @number = number
  end

  attr_reader :number

  def difference
    (square_of_sums - sum_of_squares).abs
  end

  def square_of_sums
    (1..number).reduce(:+)**2
  end

  def sum_of_squares
    (1..number).map{ |n| n**2 }.reduce(:+)
  end
end
