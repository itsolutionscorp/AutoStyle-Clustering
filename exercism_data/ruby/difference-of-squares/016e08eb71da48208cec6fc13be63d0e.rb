class Squares

  attr_reader :natural_numbers

  def initialize(input_value)
    @natural_numbers =* (1..input_value)
  end

  def sum_of_squares
    natural_numbers.reduce(0) do |sum_sq, x|
      sum_sq += x ** 2
    end
  end

  def square_of_sums
    natural_numbers.reduce(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
