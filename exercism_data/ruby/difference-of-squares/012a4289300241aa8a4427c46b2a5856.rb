class Squares
  attr_reader :range
  def initialize limit
    @range = (1..limit)
  end
  
  def square_of_sums
    @square_of_sums ||= range.reduce(:+)**2
  end
  
  def sum_of_squares
    @sum_of_squares ||= range.map{|x|x**2}.reduce(:+)
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
