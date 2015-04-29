class Squares

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    (0..@num).inject { |sum, i| sum + i ** 2 }
  end

  def square_of_sums
    (0..@num).inject { |sum, i| sum + i } ** 2
  end

  def difference
    (sum_of_squares - square_of_sums).abs()
  end

end
