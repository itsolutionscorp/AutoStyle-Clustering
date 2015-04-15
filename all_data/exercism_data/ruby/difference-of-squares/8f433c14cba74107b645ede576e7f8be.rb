class Squares
  
  def initialize i
    @i = i
  end
  def square_of_sums
    (1..@i).inject {|a,i| a + i} ** 2
  end
  def sum_of_squares
    (1..@i).inject {|a,i| a + i**2}
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
