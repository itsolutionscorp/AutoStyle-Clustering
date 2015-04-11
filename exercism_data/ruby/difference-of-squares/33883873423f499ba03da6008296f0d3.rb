class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = (1..number).reduce(:+)
    sum * sum
  end

  def sum_of_squares
    (1..number).reduce{|sum, n| sum += n * n}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
