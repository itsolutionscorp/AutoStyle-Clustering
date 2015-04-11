class Squares

  attr_reader :num

  def initialize(num)
    @num = num
  end
  
  def square_of_sums
    (0..num).reduce(:+)**2
  end

  def sum_of_squares
    (0..num).inject {|sum, n| sum + n**2}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
