class Squares
  attr_reader :number

  def initialize(number)
    @number  = number
    @sum     = 1.upto(number).reduce(:+) ** 2
    @squares = 1.upto(number).map {|n| n ** 2}.reduce(:+)
  end

  def square_of_sums
    @sum
  end

  def sum_of_squares
    @squares
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
