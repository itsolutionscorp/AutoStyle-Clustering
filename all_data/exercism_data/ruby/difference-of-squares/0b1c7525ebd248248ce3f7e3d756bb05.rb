class Squares

  def initialize(num)
    @range = (1..num)
  end

  def square_of_sums
    sum = 0
    @range.each { |i| sum += i }
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    @range.each { |i| sum += i ** 2 }
    sum
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
