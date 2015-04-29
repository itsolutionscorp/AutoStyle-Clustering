require 'pry'
class Squares
  def initialize(s)
    @squares = s
  end
  def square_of_sums
    sum = 0
    @squares.times do |cnt|
      sum = sum + (cnt + 1)
    end
    out = sum ** 2
  end
  def sum_of_squares
    sum = 0
    @squares.times do |cnt|
      sum = sum + (cnt + 1) * (cnt + 1)
    end
    sum
  end
  def difference
    out = square_of_sums - sum_of_squares
  end
end
