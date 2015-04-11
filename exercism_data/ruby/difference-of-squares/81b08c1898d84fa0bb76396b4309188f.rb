class Squares
  def initialize(number)
    @number = number
  end
  
  def square_of_sums
    sum = 0
    reps = @number + 1
    
    reps.times do |n|
      sum += n
    end
    
    sum**2
  end
  
  def sum_of_squares
    sum = 0
    reps = @number + 1
    
    reps.times do |n|
      sum = n**2 + sum
    end
    
    sum
  end
  
  def difference
    square_of_sums - sum_of_squares
  end
end
