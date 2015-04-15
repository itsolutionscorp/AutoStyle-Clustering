class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def sum_of_squares
    sequence.reduce(0) { |sum, num| sum + num**2 }
  end

  def square_of_sums
    sequence.reduce(:+) ** 2
  end

  private

  def sequence
    1..number
  end
end
