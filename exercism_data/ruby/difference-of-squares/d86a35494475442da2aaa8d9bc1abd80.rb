class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    @square_of_sums ||= (1..number).reduce(&:+) ** 2
  end

  def sum_of_squares
    @sum_of_squares ||= (1..number).map{ |number| number ** 2}.reduce(&:+)
  end

  def difference
    @difference ||= (square_of_sums - sum_of_squares)
  end
end