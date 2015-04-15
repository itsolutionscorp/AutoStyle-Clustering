class Squares
  attr_reader :num

  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..num).reduce(:+)**2  
  end

  def sum_of_squares
    (1..num).inject {|sum, num| sum + num**2 }
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

end
