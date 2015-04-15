class Squares
  def initialize(final_number)
    @final_number = final_number
  end

  def square_of_sums
    (1..@final_number).inject(&:+) ** 2
  end

  def sum_of_squares
    (1..@final_number).inject { |sum_of_squares, number|
      sum_of_squares += number ** 2
    }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
