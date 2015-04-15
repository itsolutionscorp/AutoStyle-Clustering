class Squares

  attr_accessor :numbers

  def initialize(max)
    self.numbers = (1..max).map{|number| number}
  end

  def sum_of_squares
    numbers.inject { |sum,number| sum + number**2 }
  end

  def square_of_sums
    numbers.inject(:+)**2
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

end
