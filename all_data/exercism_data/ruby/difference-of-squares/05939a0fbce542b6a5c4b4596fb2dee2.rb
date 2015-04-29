class Squares
  attr_accessor :number

  def initialize(number)
    self.number = number
  end

  def square_of_sums
    ((1..number).inject(0, :+)) ** 2
  end

  def sum_of_squares
    (1..number).inject(0) do |ret, n|
      ret += n**2
    end
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
