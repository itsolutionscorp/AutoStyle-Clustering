class Squares
  
  def initialize(num)
    @seed_num = num
  end
  
  def square_of_sums
    (1..@seed_num).inject(:+) ** 2
  end
  
  def sum_of_squares
    (1..@seed_num).inject {|sum ,e| sum += e**2 }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
    
end
