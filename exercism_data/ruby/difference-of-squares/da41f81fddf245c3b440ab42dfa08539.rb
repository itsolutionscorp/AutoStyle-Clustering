class Squares
  attr_accessor :limit
  
  def initialize(n)
    @limit = n
  end
  
  def sum_of_squares
    squares = (1..@limit).map { |n| n**2 }
    squares.reduce(:+)
  end
  
  def square_of_sums
    sums = (1..@limit).reduce(:+)
    sums ** 2
  end
  
  def difference
    square_of_sums() - sum_of_squares() 
  end
end
