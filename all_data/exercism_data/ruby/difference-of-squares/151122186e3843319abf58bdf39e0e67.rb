class Squares
  def initialize(number)
    @number = number
  end
  
  def square_of_sums
    (1..@number).reduce(:+) ** 2
  end
  
  def sum_of_squares
    (1..@number).reduce{|sum, n| sum + n**2 }
  end
  
  def difference
    (sum_of_squares - square_of_sums).abs
  end
end
