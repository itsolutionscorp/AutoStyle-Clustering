class Squares

  def initialize(n)
    @n = n
  end

  def sum_of_squares
    (1..@n).inject {|sum, i| sum += i**2}
  end


  def square_of_sums
    total = (1..@n).inject {|sum, i| sum += i}
    total = total **2
  end


  def difference
    square_of_sums - sum_of_squares
  end



end