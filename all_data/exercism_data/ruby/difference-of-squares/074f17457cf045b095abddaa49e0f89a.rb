class Squares
  def initialize num
    @num = num
  end

  def square_of_sums
    @square_of_sums ||= square_of_sums_helper
  end

  def square_of_sums_helper
    out = 0
    (0..@num).each { |i| out += i }
    out**2
  end

  def sum_of_squares
    @sum_of_squares ||= sum_of_squares_helper
  end

  def sum_of_squares_helper
    out = 0
    (0..@num).each { |i| out += i**2 }
    out
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
