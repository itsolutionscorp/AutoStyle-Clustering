class Squares

  def initialize(number)
    @number = number
  end

  def sum_of_squares
    res = 0
    (1..@number).each do |i|
      res += i**2
    end
    res
  end

  def square_of_sums
    res = 0
    (1..@number).each do |i|
      res += i
    end
    res**2
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end

end
