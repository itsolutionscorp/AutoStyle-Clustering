class Squares
  attr_reader :input_number
  def initialize(input_number)
    @input_number = input_number
  end

  def square_of_sums
    range.reduce(:+) ** 2
  end

  def sum_of_squares
    range.reduce{|sum, number| sum + number ** 2}
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def range
    @range ||= (1..input_number)
  end
end
