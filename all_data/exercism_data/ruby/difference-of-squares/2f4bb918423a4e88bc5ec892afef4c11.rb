class Squares
  def initialize num
    @num = num
  end

  def sum_of_squares
    (1..@num).inject {|s, n| s + n**2}
  end

  def square_of_sums
    (1..@num).inject(:+)**2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end