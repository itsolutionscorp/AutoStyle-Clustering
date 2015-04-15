class Squares
  def initialize value
    @max = value
  end

  def sum_of_squares
    (1..@max).inject {|sum, n| sum + n**2}
  end

  def square_of_sums
    (1..@max).reduce(:+)**2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

end
