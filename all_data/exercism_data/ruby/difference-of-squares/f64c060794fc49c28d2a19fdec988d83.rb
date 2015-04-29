class Squares

  attr_accessor :number

  def initialize(number)
    self.number = number
  end

  def sum_of_squares
    sum = 0
    (1..self.number).each do |n|
      sum += n**2 
    end
    sum
  end

  def square_of_sums
    sum = 0
    (1..self.number).each do |n|
      sum += n 
    end
    sum**2
  end

  def difference
    square_of_sums - sum_of_squares
  end

end
