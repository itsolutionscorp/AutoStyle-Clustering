class Squares

  attr_accessor :num, :sum_quares, :squares_sum

  def initialize(num)
    @num = num
  end

  def sum_of_squares
    @sum_squares ||= (0..@num).reduce{ |a,b| a + b ** 2 }
  end

  def square_of_sums
    @squares_sum ||= (0..@num).reduce{ |a,b| a + b } ** 2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

end
