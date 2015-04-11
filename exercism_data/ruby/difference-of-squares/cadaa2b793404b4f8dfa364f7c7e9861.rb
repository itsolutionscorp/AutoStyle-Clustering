# Squares exercise
# Written in 2014 by Benjamin Shyong <hello@benshyong.com>

class Squares

  def initialize(num)
    @num = num
  end

  def square_of_sums
    (1..@num).inject{|s, n| s += n} ** 2
  end

  def sum_of_squares
    (1..@num).inject{|s, n| s += n*n}
  end

  def difference
     square_of_sums - sum_of_squares
  end

end
