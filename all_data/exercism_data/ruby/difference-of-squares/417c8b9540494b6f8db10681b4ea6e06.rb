class Squares

  attr_accessor :number

  def initialize(number)
    self.number = number
  end

  def square_of_sums
    (1..number).reduce(:+) ** 2
  end

  def sum_of_squares
    (1..number).reduce(0) { |memo, n| memo + n ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
