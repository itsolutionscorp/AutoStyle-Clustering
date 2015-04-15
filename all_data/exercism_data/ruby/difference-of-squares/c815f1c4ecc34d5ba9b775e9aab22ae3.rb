class Squares
  attr_accessor :number

  def initialize(num)
    @number = num
  end

  def square_of_sums
    (1..number).inject(0) { |result, element| result + element } ** 2
  end

  def sum_of_squares
    (1..number).inject(0) { |result, element| result + element ** 2 }
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
