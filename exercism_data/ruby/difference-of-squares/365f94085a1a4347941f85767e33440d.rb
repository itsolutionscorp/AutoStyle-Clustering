class Squares
  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).inject(0) { |sum, i| sum + i } ** 2
  end

  def sum_of_squares
    (1..@num).inject(0) { |sum, i| sum + i**2 }
  end

  def difference
    sum_of_squares - square_of_sums
  end
end
