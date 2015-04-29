class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    number.downto(1).reduce(&:+) ** 2
  end

  def sum_of_squares
    number.downto(1).map { |n| n ** 2 }.reduce(&:+)
  end
end
