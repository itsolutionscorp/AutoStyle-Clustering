class Squares
  def initialize(up_to)
    @up_to = up_to
  end

  def sum_of_squares
    (1..@up_to).inject {|sum, num| sum + (num ** 2)}
  end

  def square_of_sums
    (1..@up_to).inject(:+) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
