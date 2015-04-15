class Squares

  attr_accessor :number

  def initialize number
    self.number = number
  end

  def square_of_sums
    (1..(self.number)).to_a.inject(:+) ** 2
  end

  def sum_of_squares
    (1..(self.number)).to_a.map { |num| num ** 2 } .inject(:+)
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end

puts Squares.new(100).difference
