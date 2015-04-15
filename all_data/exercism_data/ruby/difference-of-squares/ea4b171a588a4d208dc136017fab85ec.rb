class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    (1..number).inject(0) { |sum, val| sum + val }**2
  end

  def sum_of_squares
    1.upto(number).map { |x| x**2 }.inject(0) { |sum, val| sum + val }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  attr_reader :number
end
