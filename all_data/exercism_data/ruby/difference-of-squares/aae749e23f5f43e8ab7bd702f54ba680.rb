class Squares
  def initialize(num)
    @num=num
  end
  def square_of_sums
    (1..@num).to_a.inject(:+)**2
  end
  def sum_of_squares
    sum = 0
    (1..@num).each.to_a.inject  { |s,x| s + x**2 }
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
