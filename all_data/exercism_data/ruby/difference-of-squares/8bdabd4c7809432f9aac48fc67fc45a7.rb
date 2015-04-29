class Squares
  def initialize(final_number)
    @number_range = (1..final_number)
  end

  def square_of_sums
    @number_range.inject(&:+) ** 2
  end

  def sum_of_squares
    @number_range.inject { |sum, number| sum += number ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
