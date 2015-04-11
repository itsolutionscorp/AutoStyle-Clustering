class Squares
  def initialize up_to
    @up_to = up_to
  end

  def square_of_sums
    1.upto(@up_to).inject(:+)**2
  end

  def sum_of_squares
    1.upto(@up_to).inject { |sum, n| sum + n**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
