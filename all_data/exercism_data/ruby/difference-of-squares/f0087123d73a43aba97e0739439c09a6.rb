class Squares
  attr_accessor :number

  def initialize(number)
    @number = number
  end

  def sum_of_squares
    (1..number).inject(0) { |sum, integer| sum += (integer**2) }
  end

  def square_of_sums
    sum = (1..number).inject(0) { |sum, integer| sum += integer }
    sum ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
