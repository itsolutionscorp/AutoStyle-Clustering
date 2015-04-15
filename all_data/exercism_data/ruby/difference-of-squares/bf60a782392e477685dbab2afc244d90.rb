# Squares class

class Squares
  def initialize( num )
    @list = 1..num
  end

  def sum_of_squares
    @list.reduce( 0 ) { |a, e| a + e * e }
  end

  def square_of_sums
    @list.reduce( :+ ) ** 2
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
