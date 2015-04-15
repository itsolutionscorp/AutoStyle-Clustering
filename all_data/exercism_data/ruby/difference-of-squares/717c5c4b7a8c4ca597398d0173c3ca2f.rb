class Squares
  def initialize(number)
    @number = number
  end

  def sum_of_squares
    sum = 0
    to_array(@number).each { |n| sum += (n*n) }
    sum
  end

  def square_of_sums
    square = 0
    to_array(@number).each { |n| square+= n }
    square**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def to_array(number)
    array = []
    (1..number).each { |n| array << n }
    array
  end
end
