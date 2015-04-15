class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def difference
    square_of_sums - sum_of_squares
  end

  def square_of_sums
    natural_numbers.reduce(&:+) ** 2
  end

  def sum_of_squares
    natural_numbers.map { |n| n ** 2 }.reduce(&:+)
  end

  private

  def natural_numbers
    number.downto(1)
  end
end
