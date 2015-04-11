class MyNum < DelegateClass(Fixnum)
  def square
    MyNum.new self * self
  end
end

class Squares
  attr_reader :number

  def initialize(number)
    @number = number
  end

  def square_of_sums
    range.reduce(&:+) ** 2
  end

  def sum_of_squares
    range.map(&:square).reduce(&:+)
  end

  def difference
    square_of_sums - sum_of_squares
  end

  private

  def range
    (1..number).map { |n| MyNum.new n }
  end
end
