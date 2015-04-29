class Squares

  def initialize x
    @num = x
  end

  def sum_of_squares
   1.upto(@num).inject{ |a, i| a += (i**2) }
  end

  def square_of_sums
   res = 1.upto(@num).inject { |a, i| a += i }
   res**2
  end

  def difference
     square_of_sums - sum_of_squares
  end

end
