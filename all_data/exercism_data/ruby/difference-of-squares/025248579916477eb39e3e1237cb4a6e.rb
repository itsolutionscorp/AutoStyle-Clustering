class Squares
  def initialize(number)
    @num = number
  end
  
  def square_of_sums
    (1..@num).inject(:+) ** 2
  end
  
  def sum_of_squares
    (1..@num).inject(0){ |sum, num| sum += num ** 2 }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
