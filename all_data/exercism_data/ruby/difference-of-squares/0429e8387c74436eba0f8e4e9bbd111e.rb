class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    (1.upto(number).inject(:+))**2
  end

  def sum_of_squares
    1.upto(number).reduce { |total, number| total + number**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
