class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = 0
    (1..number).each { |i| sum += i }
    sum **= 2
  end

  def sum_of_squares
    sum = 0
    (1..number).each { |i| sum += i**2 }
    sum
  end

  def difference
     square_of_sums - sum_of_squares
  end
end
