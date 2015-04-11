class Squares
  attr_accessor :range
  def initialize range  
    self.range = range
  end

  def square_of_sums
    ((0..range).inject {|a, b| a + b}) ** 2
  end

  def sum_of_squares
    (0..range).inject {|acc, num| acc += (num ** 2) }
  end

  def difference
    square_of_sums - sum_of_squares 
  end

end
