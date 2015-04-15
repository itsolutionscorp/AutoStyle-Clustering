# ~/exercism/ruby/difference-of-squares/difference_of_squares.rb
class Squares
  def initialize(arg)
    @number = arg
  end

  def square_of_sums
    (1..@number).inject(:+)**2
  end

  def sum_of_squares
    (1..@number).inject { |n| + n**2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
