class Squares

  def initialize(max_number)
    @range = 1..max_number
  end

  def square_of_sums
    @range.inject(:+).abs2
  end

  def sum_of_squares
    @range.inject { |sum, n| sum + n.abs2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
