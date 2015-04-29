class Squares

  def self.sum_of_squares(num)
    sum = 0
    for i in 1..num do
      sum += i ** 2
    end
    return sum
  end

  def self.square_of_sums(num)
    sum = 0
    for i in 1..num do
      sum += i
    end
    square = sum ** 2
    return square
  end

  def self.difference(num)
    diff = self.square_of_sums(num) - self.sum_of_squares(num)
    return diff
  end

end
