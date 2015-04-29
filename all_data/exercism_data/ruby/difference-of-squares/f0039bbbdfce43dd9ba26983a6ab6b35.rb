class Squares
  attr_accessor :number, :square, :sum

  def initialize(number)
    @number = number
  end

  def square_of_sums
    @square = ((1..number).inject {|sum, x| sum + x})**2
  end

  def sum_of_squares
    @sum = ((1..number).to_a.map! {|x| x**2}).inject {|sum, x| sum + x}
  end

  def difference
    Squares.new(number).square_of_sums - Squares.new(number).sum_of_squares
  end
end
