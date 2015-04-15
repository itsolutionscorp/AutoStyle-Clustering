class Squares

  def initialize(number)
    @number = number
  end

  def square_of_sums
    sum = 0
    (0..@number).each do |num|
      sum += num
    end
    sum * sum
  end

  def sum_of_squares
    sum = 0
    (0..@number).each do |num|
      temp = num * num
      sum += temp
    end
    sum
  end

  def difference
    self.square_of_sums - self.sum_of_squares
  end


end
