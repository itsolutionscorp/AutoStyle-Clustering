class Squares < Struct.new(:count)

  def square_of_sums
    (1..count).inject(:+) ** 2
  end

  def sum_of_squares
    (1..count).inject { |sum, number| sum + (number ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
