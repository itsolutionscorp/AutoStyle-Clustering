class Squares
  def initialize(number)
    @number = number
  end
  def sum_of_squares
    sum = 0
    (@number + 1).times {|n| sum += n**2 }
    sum
  end
  def square_of_sums
    sum = 0
    (@number + 1).times {|n| sum += n }
    sum**2
  end
  def difference
    square_of_sums - sum_of_squares
  end
end
