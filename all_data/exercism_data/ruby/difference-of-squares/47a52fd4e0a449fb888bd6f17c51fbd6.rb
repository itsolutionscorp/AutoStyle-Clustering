class Squares
  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = 0

    (1..@number).each { |num| sum += num }

    sum ** 2
  end

  def sum_of_squares
    sum = 0

    (1..@number).each { |num| sum += num ** 2 }

    sum
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end
end
