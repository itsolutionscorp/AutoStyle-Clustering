class Squares
  attr_reader :range

  def initialize(maximum_number)
    @range = (1..maximum_number.to_i)
  end

  def sum_of_squares
    range.inject {|accumulator, number| accumulator + number ** 2 }
  end

  def square_of_sums
    range.inject(&:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
