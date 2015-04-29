class Squares

  def initialize(number)
    @number = number
  end

  attr_reader :number

  def square_of_sums
    sum = 0
    (0..number).each {|n| sum += n}
    sum*sum
  end

  def sum_of_squares
    sum = 0
    (0..number).each {|n| sum += n*n}
    sum
  end

  def difference 
    square_of_sums - sum_of_squares
  end
end
