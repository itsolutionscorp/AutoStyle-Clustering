class Squares
  def initialize(squares)
    @squares = squares
    @sum_of_squares
    @square_of_sums
  end


  def square_of_sums
    sum = 0
    (1..@squares).each{|x| sum += x}
    @square_of_sums = sum**2
  end

  def sum_of_squares
    @sum_of_squares = 0
    (1..@squares).each{|x|
      @sum_of_squares += x**2
    }
    @sum_of_squares
  end

  def difference
    square_of_sums - sum_of_squares
  end
end

p Squares.new(5).difference
p Squares.new(5).sum_of_squares
p Squares.new(5).square_of_sums
