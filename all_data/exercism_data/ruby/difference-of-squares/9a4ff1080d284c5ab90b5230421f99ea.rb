class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    sum = (1..@num).inject(:+)
    sum**2
  end

  def sum_of_squares
    squared_array = (1..@num).map { |nums| nums**2}
    squared_array.inject(:+)
  end

  def difference
    return square_of_sums - sum_of_squares
  end


end
