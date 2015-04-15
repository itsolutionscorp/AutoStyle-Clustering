class Squares
  attr_reader :number

  def initialize number
    @number = number
  end

  def square_of_sums
    sum = 0
    (1..number).map { |i| sum += i }
    sum**2
  end

  def sum_of_squares
    sum = 0
    (1..number).map { |i| sum += i**2 }
    sum
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
