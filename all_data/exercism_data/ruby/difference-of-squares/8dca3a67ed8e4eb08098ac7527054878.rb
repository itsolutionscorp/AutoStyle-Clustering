class Squares
  attr_accessor :n

  def initialize(n)
    @n = n
    return n
  end

  def square_of_sums
    sum = 0
    (1..@n).each do |i|
      sum += i
    end
    sum ** 2
  end

  def sum_of_squares
    sum = 0
    (1..@n).each do |i|
      sum += i ** 2
    end
    sum
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
