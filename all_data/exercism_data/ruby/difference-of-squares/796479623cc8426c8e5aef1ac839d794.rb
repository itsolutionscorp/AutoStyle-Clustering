class Squares
  def initialize(num)
    @num = num

  end

  def square_of_sums
    @sum = (1..@num).inject {|sum, i| sum+=i}
    return @sum**2
  end

  def sum_of_squares
    (1..@num).inject {|sum, i| sum+(i**2)}
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
