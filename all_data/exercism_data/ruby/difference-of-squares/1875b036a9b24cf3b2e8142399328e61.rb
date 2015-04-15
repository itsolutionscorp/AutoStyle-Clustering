# squares.rb
# author: Ray Wach
# date: 2015-01-12

class Squares
  def initialize(n)
    @range = 1.upto(n)
  end
  def sum_of_squares
    @sum_of_squares ||= @range.inject {|acc, i| acc + i**2}
  end
  def square_of_sums
    @square_of_sums ||= @range.reduce(:+)**2
  end
  def difference
    @difference ||= square_of_sums - sum_of_squares
  end
end
