class Squares < Struct.new(:number)
  def square_of_sums
    range.inject(:+) ** 2
  end

  def sum_of_squares
    squares.inject(:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

protected

  def range
    1..number
  end

  def squares
    range.map { |n| n ** 2 }
  end
end
