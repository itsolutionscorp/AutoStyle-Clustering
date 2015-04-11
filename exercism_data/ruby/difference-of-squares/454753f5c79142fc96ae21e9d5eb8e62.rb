class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sequence.reduce(0, :+) ** 2
  end

  def sum_of_squares
    sequence.reduce(0) { |sum, x| sum + x ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def sequence
    1 .. number
  end
end
