class Squares
  def initialize number
    @range = number
  end

  def square_of_sums
    (0..@range).reduce(:+) ** 2
  end

  def sum_of_squares
    (0..@range).reduce(0) { |sum,num| sum + num ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
