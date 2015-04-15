class Squares
  attr_reader :number
  
  def initialize(number)
    @number = number
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
  
  def sum_of_squares
    sum_o_sq = 0
    number.times do |n|
      n += 1
      sum_o_sq += (n**2)
    end
    sum_o_sq
  end
  
  def square_of_sums
    sq_o_sum = 0
    number.times do |n|
      n += 1
      sq_o_sum += n
    end
    sq_o_sum ** 2
  end
  
end
