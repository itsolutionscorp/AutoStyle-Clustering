class Squares

  def initialize(max_number)
    @max_number = 1..max_number
  end

  def square_of_sums
    @max_number.inject(:+).abs2
  end

  def sum_of_squares
    @max_number.inject { |sum, n| sum + n.abs2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
