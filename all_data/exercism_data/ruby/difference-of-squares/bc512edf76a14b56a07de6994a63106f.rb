class Squares

  def initialize(num)
    @num = num
  end
  
  def square_of_sums
    square = 0
    (1..@num).each do |n|
      square += n
    end
    square = square**2
    return square
  end
  
  def sum_of_squares
    sum = 0
    (1..@num).each do |n|
      sum += n**2
    end
    return sum
  end
  
  def difference
    square_of_sums - sum_of_squares
  end

end
