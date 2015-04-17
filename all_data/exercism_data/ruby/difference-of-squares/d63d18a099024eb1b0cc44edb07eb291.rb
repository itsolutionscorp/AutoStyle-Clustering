class Squares
  def initialize(i)
    @int = i
  end
  
  def square_of_sums
    ((1..@int).inject { |sum, n| sum + n })**2
  end
  
  def sum_of_squares
    (1..@int).inject { |sum, n| sum + n**2 }
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end