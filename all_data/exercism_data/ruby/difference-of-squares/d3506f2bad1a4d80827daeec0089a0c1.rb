class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    sequence.reduce(:+) ** 2
  end

  def sum_of_squares
    sequence.map { |n| n ** 2 }.reduce(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private
  attr_reader :number

  def sequence
    (1..number)
  end
end
