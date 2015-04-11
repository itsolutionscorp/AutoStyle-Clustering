class Squares
  attr_reader :number
  def initialize(n)
    @number = n
  end

  def square_of_sums
    (1..number).inject{|x,y| x+y} ** 2
  end

  def sum_of_squares
    (1..number).map { |e| e**2 }.inject{ |x,y| x + y  }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
